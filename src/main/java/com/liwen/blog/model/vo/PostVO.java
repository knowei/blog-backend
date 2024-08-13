package com.liwen.blog.model.vo;

import com.liwen.blog.model.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 帖子视图
 *
 * @author knowei
 * @TableName product
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostVO extends Post {

    private List<Integer> tags;

    private static final long serialVersionUID = 1L;
}