package com.liwen.blog.model.dto.post;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class PostUpdateDto implements Serializable {


    private Integer id;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Integer categoryId;


    /**
     *
     */
    private Date updatedAt;


    private static final long serialVersionUID = 1L;
}