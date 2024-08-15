package com.liwen.blog.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommentVo implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer postId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 一级评论
     */
    private Integer treeId;

    /**
     * 点赞次数
     */
    private Integer commentLike;

    /**
     * 二级评论
     */
    private Integer parentId;

    private List<CommentVo> commentVoList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
