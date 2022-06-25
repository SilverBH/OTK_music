package com.zsc.otaku_music.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("music_list_item_info")
public class MusicListItem implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long itemId;
    @TableField("status")
    private Integer status;
    @TableField("update_time")
    private Date updateTime;
    @TableField("list_id")
    private Long listId;
}
