package com.liwen.blog.mapper;

import com.liwen.blog.model.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zheng
* @description 针对表【tb_comment】的数据库操作Mapper
* @createDate 2024-08-15 13:52:20
* @Entity com.liwen.blog.model.entity.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取评论信息，如果没有commentId，那么获取的是一级评论，不然获取二级评论
     * @param comment_id
     * @param id
     * @return
     */
    List<Comment> getByPostId(@Param("comment_id")Integer comment_id, @Param("post_id") Integer post_id);
}




