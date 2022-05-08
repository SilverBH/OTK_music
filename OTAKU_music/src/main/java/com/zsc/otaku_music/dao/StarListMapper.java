package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.StarList;
import com.zsc.otaku_music.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface StarListMapper extends BaseMapper<StarList> {
    // 插入一条记录
    int insert(StarList starList);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) StarList starList);

    // 根据 ID 查询
    StarList selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<StarList> selectList(@Param(Constants.WRAPPER) Wrapper<StarList> queryWrapper);
}
