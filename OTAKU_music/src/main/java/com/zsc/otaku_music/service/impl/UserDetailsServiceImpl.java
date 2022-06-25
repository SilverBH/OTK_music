package com.zsc.otaku_music.service.impl;

import com.zsc.otaku_music.model.Authority;
import com.zsc.otaku_music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description 自定义UserDetailsService实现类进行用户认证信息封装
 * @Author Yooomu
 * @Date 2022/4/30 15:02
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImpl.getUser(username);
        List<Authority> authorityList = userServiceImpl.getUserAuthority(username);
        // 对用户权限进行封装
        List<SimpleGrantedAuthority> list =
                authorityList.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                        .collect(Collectors.toList());
        // 返回封装的UserDetails用户详情类
        if (user != null) {
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(user.getLoginName(),user.getPassWord(),list);
            return userDetails;
        } else {
            // 若查询用户不存在，必须抛出此异常
            throw new UsernameNotFoundException("当前用户不存在！");
        }
    }
}
