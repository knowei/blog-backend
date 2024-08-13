package com.liwen.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_post_tag
 */
@TableName(value ="tb_post_tag")
@Data
public class PostTag implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 
     */
    private Integer postId;

    /**
     * 
     */
    private Integer tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}