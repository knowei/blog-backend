package com.liwen.blog.model.dto.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liwen.blog.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询请求
 *
 * @author knowei
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryDto extends PageRequest implements Serializable {

    @TableId(type = IdType.AUTO)
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
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;


    private static final long serialVersionUID = 1L;
}