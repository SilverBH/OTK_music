package com.zsc.otaku_music.config;

import com.zsc.otaku_music.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author Yooomu
 * @Date 2022/4/30 15:37
 **/
@EnableWebSecurity  // 组合注解，开启MVC Security 安全支持
// 启动权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    /**
//     * 强散列哈希加密实现
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    /**
     * 持久化Token存储
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
        jtr.setDataSource(dataSource);
        return jtr;
    }

    /**
     * 使用UserDetails进行身份验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 密码编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 使用UserDetails进行认证
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);


        //设置密码编码器
        //内存身份验证(用于测试)
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
                = auth.inMemoryAuthentication().passwordEncoder(encoder);
        //模拟两个用户
        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer.withUser("satori")
                .password(encoder.encode("123"))
                .roles("common");
        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer.withUser("yooomu")
                .password(encoder.encode("123"))
                .roles("vip");
    }

    /**
     * 自定义用户访问控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/css/**","/image/**","/js/**").permitAll()   //放行此路径下所有请求
                .antMatchers("/common/**").hasRole("common")    //访问此路径需common权限
                .antMatchers("/vip/**").hasRole("vip")  //访问此路径需要vip权限
                .anyRequest().authenticated();   //除上述路径外所有路径的访问都需要登陆
//                .and().formLogin(); //配置security默认登陆页面

        http.formLogin().loginPage("/userLogin").permitAll()    //修改登录页面并放行
                .usernameParameter("name")  //对应登陆表单中用户名的name字段
                .passwordParameter("pwd")   //对应登陆表单中密码的name字段
                .defaultSuccessUrl("/")     //登陆成功后跳转
//                .defaultSuccessUrl("/getUser")     //登陆成功后跳转
                .failureUrl("/userLogin?error");    //登陆失败后跳转

        http.logout().logoutUrl("/userLogout").permitAll()    //注销路径
                .logoutSuccessUrl("/");     //注销成功后跳转

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .tokenRepository(tokenRepository());

        http.csrf().disable();
    }
}
