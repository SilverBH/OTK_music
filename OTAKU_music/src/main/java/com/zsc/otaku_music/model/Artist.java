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
@TableName("artist_info")
public class Artist implements Serializable {
    @TableId
    private Long artistId;
    @TableField("artist_name")
    private String artistName;
    @TableField("status")
    private Integer status;
    @TableField("update_time")
    private Date updateTime;
}
