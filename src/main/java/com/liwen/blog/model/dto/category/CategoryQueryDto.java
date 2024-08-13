package com.liwen.blog.model.dto.category;

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
public class CategoryQueryDto extends PageRequest implements Serializable {

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Date createdAt;

    private static final long serialVersionUID = 1L;
}