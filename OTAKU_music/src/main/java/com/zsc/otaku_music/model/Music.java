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
@TableName("music_info")
public class Music implements Serializable {
    @TableId
    private Long musicId;
    @TableField("music_name")
    private String musicName;
    @TableField("music_url")
    private String musicUrl;
    @TableField("pic_url")
    private String picUrl;
    @TableField("artist_id")
    private Long artistId;
    @TableField("status")
    private Integer status;
    @TableField("update_time")
    private Date updateTime;
}
