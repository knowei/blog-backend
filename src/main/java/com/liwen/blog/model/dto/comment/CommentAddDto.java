package com.liwen.blog.model.dto.comment;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentAddDto implements Serializable {

    /**
     * 评论内容
     */
    private String content;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章id
     */
    private Integer postId;

    /**
     * 评论
     */
    private Integer commentId;


    private static final long serialVersionUID = 1L;
}
