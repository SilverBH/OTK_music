package com.zsc.otaku_music.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.otaku_music.Utils.MailUtils;
import com.zsc.otaku_music.dao.AuthorityMapper;
import com.zsc.otaku_music.dao.UserMapper;
import com.zsc.otaku_music.dto.UserAddDto;
import com.zsc.otaku_music.dto.UserFindDto;
import com.zsc.otaku_music.dto.UserUpdateDto;
import com.zsc.otaku_music.excption.ServiceException;
import com.zsc.otaku_music.model.Authority;
import com.zsc.otaku_music.model.PageResult;
import com.zsc.otaku_music.model.User;
import com.zsc.otaku_music.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName UserService
 * @Description 用户数据结合Redis缓存进行业务处理
 * @Author Yooomu
 * @Date 2022/4/30 15:01
 **/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailUtils mailUtils;

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
//            authorityList.forEach(System.out::println);
            if (authorityList.size() > 0) {
                // 存在即存入redis缓存
                redisTemplate.opsForValue().set("authorityList_" + username, authorityList);
            }
        }
        return authorityList;
    }

    /**
     * 分页查询
     * @param userFindDto
     * @return
     */
    @Override
    public PageResult<User> pageQuery(UserFindDto userFindDto) {
//         PageHelper.startPage(userFindDto.getCurrent(), userFindDto.getPageSize());
//         PageInfo<User> pageInfo = new PageInfo<User>(baseMapper.pageQuery(userFindDto));
//         //查询结果统一转换为pageResult格式
//         PageResult<User> pageResult = PageResult.fromPageInfo(pageInfo);
        //使用mybatis-实现分页查询,构造分页查询条件
        Page result = lambdaQuery()
                .like(StringUtils.isNotBlank(userFindDto.getLoginName()), User::getLoginName, userFindDto.getLoginName())
                .like(StringUtils.isNotBlank(userFindDto.getEmail()), User::getEmail, userFindDto.getEmail())
                .eq(userFindDto.getStatus() != null, User::getStatus, userFindDto.getStatus())
                .page(userFindDto.toPage());

        return PageResult.fromPage(result);
    }

    @Override
    public void save(UserAddDto userAddDto) {
        User user = new User();
        user.setLoginName(userAddDto.getLoginName());
        user.setEmail(userAddDto.getEmail());
        user.setStatus(userAddDto.getStatus());
        if (this.save(user) == false) {
            throw new ServiceException("用户创建失败");
        }

    }

    @Override
    public void updateById(UserUpdateDto userUpdateDto) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDto, user);
        if (this.updateById(user) == false) {
            throw new ServiceException("用户保存失败");
        }
    }

    /**
     * 重置密码，生成新密码发送至指定邮箱
     * @param id
     * @return
     */
    @Override
    public String resetPwd(Long id) {
        // 密码编码器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = super.getById(id);
        // 生成新的随机密码
        String newPassword = user.getLoginName().substring(0, 2) + RandomUtil.randomString(4);
        // 更新密码
        super.lambdaUpdate().eq(User::getId, id).set(User::getPassWord, passwordEncoder.encode(newPassword));

        // 如果email地址不合法, 直接返回密码给管理员
        if (user.getEmail() == null && Validator.isEmail(user.getEmail())) {
            return "密码重置成功,新密码为:" + newPassword;
        } else {
            // 发送新密码到用户邮箱
            String subject = "密码重置通知";
            Map data = new HashMap<String, String>();
            data.put("username", user.getLoginName());
            // 生成随机6位字符串密码
            data.put("password", user.getLoginName().substring(0, 2) + RandomUtil.randomString(4));
            try {
                mailUtils.sendTemplateMail(user.getEmail(), subject, "resetPwd.html", data);
                return "密码重置成功,新密码已经发到用户指定邮箱";
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new ServiceException("邮件发送失败");
            }
        }
    }


    @Override
    public void batchDel(List<Long> ids) {
        super.removeByIds(ids);
    }

//    /**
//     * 根据用户名（对应数据库login_name）获取用户
//     * @param username
//     * @return
//     */
//    @Override
//    public User getOne(QueryWrapper<User> username) {
//        return super.getOne(username);
//    }


}
