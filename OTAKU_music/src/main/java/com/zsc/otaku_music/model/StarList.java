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
@TableName("star_list_info")
public class StarList implements Serializable {
    @TableId
    private Long starListId;
    @TableField("status")
    private Integer status;
    @TableField("update_time")
    private Date updateTime;
    @TableField("user_id")
    private Long userId;
}
