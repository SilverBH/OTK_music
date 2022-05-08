package com.zsc.otaku_music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsc.otaku_music.dao.AuthorityMapper;
import com.zsc.otaku_music.dao.UserMapper;
import com.zsc.otaku_music.model.Authority;
import com.zsc.otaku_music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description 用户数据结合Redis缓存进行业务处理
 * @Author Yooomu
 * @Date 2022/4/30 15:01
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 业务控制，唯一用户名查询用户信息
     * @param username
     * @return
     */
    public User getUser(String username) {
        User user = null;
        Object obj = redisTemplate.opsForValue().get("user_" + username);
        if (obj != null) {
            // redis缓存中存在,直接获取
            user = (User) obj;
        } else {
            // 无则数据库查询并保存至redis
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("login_name",username);
            user = userMapper.selectOne(wrapper);
            if (user != null) {
                redisTemplate.opsForValue().set("user_" + username,user);
            }
        }
        return user;
    }

    /**
     * 业务控制，用户名查询用户权限
     * @param username
     * @return
     */
    public List<Authority> getUserAuthority(String username) {
        List<Authority> authorityList = null;
        Object obj = redisTemplate.opsForValue().get("authorityList_" + username);
        if (obj != null) {
            authorityList = (List<Authority>) obj;
        } else {
            authorityList = authorityMapper.findAuthorityByUserName(username);
            if (authorityList.size() > 0) {
                // 存在即存入redis缓存
                redisTemplate.opsForValue().set("authorityList_" + username, authorityList);
            }
        }
        return authorityList;
    }
}
