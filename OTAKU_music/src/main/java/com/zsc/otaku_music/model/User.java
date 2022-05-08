package com.zsc.otaku_music.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class User implements Serializable {
    @TableId
    private Long id;
    @TableField("login_name")
    private String loginName;
    @TableField("pass_word")
    private String passWord;
    @TableField("email")
    private String email;
    @TableField("register_time")
    private Date registerTime;
    @TableField("register_ip")
    private String registerIp;
    @TableField("last_login_time")
    private Date lastLoginTime;
    @TableField("last_login_ip")
    private String lastLoginIp;
    @TableField("status")
    private Integer status;
    @TableField("avatar")
    private String avatar;
}
