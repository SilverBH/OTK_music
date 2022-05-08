package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.Music;
import com.zsc.otaku_music.model.MusicList;
import com.zsc.otaku_music.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface MusicListMapper extends BaseMapper<MusicList> {
//    // 根据id查询
//    @Select("select * from music_list_info where music_list_id = #{musicListId}")
//    public MusicList queryById(Integer musicListId);
//
//    // 查询所有
//    @Select("select * from music_list_info")
//    public List<MusicList> queryAll();
//
//    // 插入方法
//    @Insert("insert into music_list_info values(#{musicListId},#{musicListName},#{status},#{remark},#{updateTime},#{userId})")
//    public void insertMusicList(MusicList musicList);
//
//    // 修改方法
//    @Update("update music_list_info set `music_list_name`=#{musicListName},`status`=#{status},`remark`=#{remark},`update_time`=#{updateTime},`user_id`=#{userId} where `music_list_id`=#{musicListId}")
//    public void updateMusicList(MusicList musicList);
//
//    // 删除方法
//    @Delete("delete from music_list_info where `music_list_id` = #{musicListId}")
//    public void deleteMusicListById(Integer musicListId);

    // 插入一条记录
    int insert(MusicList musicList);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) MusicList musicList);

    // 根据 ID 查询
    MusicList selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<MusicList> selectList(@Param(Constants.WRAPPER) Wrapper<MusicList> queryWrapper);
}
