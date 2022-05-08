package com.zsc.otaku_music.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsc.otaku_music.model.Artist;
import com.zsc.otaku_music.model.Music;
import com.zsc.otaku_music.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
//    // 根据id查询
//    @Select("select * from music_info where music_id = #{musicId}")
//    public Music queryById(Integer musicId);
//
//    // 查询所有
//    @Select("select * from music_info")
//    public List<Music> queryAll();
//
//    // 插入方法
//    @Insert("insert into music_info values(#{musicId},#{musicName},#{musicUrl},#{picUrl},#{artistId},#{status},#{remark},#{updateTime})")
//    public void insertMusic(Music music);
//
//    // 修改方法
//    @Update("update music_info set `music_name`=#{musicName},`music_url`=#{musicUrl},`pic_url`=#{picUrl},`artist_id`=#{artistId},`status`=#{status},`remark`=#{remark},`update_time`=#{updateTime} where `music_id`=#{musicId}")
//    public void updateMusic(Music music);
//
//    // 删除方法
//    @Delete("delete from music_info where `music_id` = #{musicId}")
//    public void deleteMusicById(Integer musicId);

    // 插入一条记录
    int insert(Music music);

    // 根据 ID 删除
    int deleteById(Serializable id);

    // 根据传入实体的 ID 修改
    int updateById(@Param(Constants.ENTITY) Music music);

    // 根据 ID 查询
    Music selectById(Serializable id);

    // 根据 entity 条件，查询全部记录
    List<Music> selectList(@Param(Constants.WRAPPER) Wrapper<Music> queryWrapper);
}
