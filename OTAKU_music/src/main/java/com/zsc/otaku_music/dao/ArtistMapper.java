package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.Artist;
import com.zsc.otaku_music.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Mapper
public interface ArtistMapper extends BaseMapper<Artist> {
//    // 根据id查询
//    @Select("select * from artist_info where artist_id = #{artistId}")
//    public Artist queryById(Integer artistId);
//
//    // 查询所有
//    @Select("select * from artist_info")
//    public List<Artist> queryAll();
//
//    // 插入方法
//    @Insert("insert into artist_info values(#{artistId},#{artistName},#{status},#{remark},#{updateTime})")
//    public void insertArtist(Artist artist);
//
//    // 修改方法
//    @Update("update artist_info set `artist_name`=#{artistName},`status`=#{status},`remark`=#{remark},`update_time`=#{updateTime} where `artist_id`=#{artistId}")
//    public void updateArtist(Artist artist);
//
//    // 删除方法
//    @Delete("delete from artist_info where `artist_id` = #{artistId}")
//    public void deleteUserById(Integer artistId);

    // 插入一条记录
    int insert(Artist artist);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) Artist artist);

    // 根据 ID 查询
    Artist selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<Artist> selectList(@Param(Constants.WRAPPER) Wrapper<Artist> queryWrapper);
}
