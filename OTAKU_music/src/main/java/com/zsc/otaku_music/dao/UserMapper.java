package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    // 根据id查询
//    @Select("select * from user_info where id = #{id}")
//    public User queryById(Integer id);
//
//    // 查询所有
//    @Select("select * from user_info")
//    public List<User> queryAll();
//
//    // 插入方法
//    @Insert("insert into user_info values(#{id},#{loginName},#{passWord},#{email},#{registerTime},#{registerIp},#{lastLoginTime},#{lastLoginIp},#{status})")
//    public void insertUser(User user);
//
//    // 修改方法
//    @Update("update user_info set `login_name`=#{loginName},`pass_word`=#{passWord},`email`=#{email},`register_time`=#{registerTime},`register_ip`=#{registerIp},`last_login_time`=#{lastLoginTime},`last_login_ip`=#{lastLoginIp},`status`=#{status} where `id`=#{id}")
//    public void updateUser(User user);
//
//    // 删除方法
//    @Delete("delete from user_info where `id` = #{id}")
//    public void deleteUserById(Integer id);


    // 插入一条记录
    int insert(User user);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) User user);

    // 根据 ID 查询
    User selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<User> selectList(@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

}
