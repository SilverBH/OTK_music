package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.Artist;
import com.zsc.otaku_music.model.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
    // 插入一条记录
    int insert(Authority authority);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) Authority authority);

    // 根据 ID 查询
    Authority selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<Authority> selectList(@Param(Constants.WRAPPER) Wrapper<Authority> queryWrapper);

    // 根据用户名查找用户权限
    @Select("select a.* " +
            "from user_info u,authority_info a,user_authority ua " +
            "where u.login_name=#{username} and ua.user_id=u.id and ua.authority_id=a.authority_id")
    List<Authority> findAuthorityByUserName(String username);

}
