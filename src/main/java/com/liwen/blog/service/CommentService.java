package com.liwen.blog.service;

import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.model.dto.comment.CommentAddDto;
import com.liwen.blog.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zheng
* @description 针对表【tb_comment】的数据库操作Service
* @createDate 2024-08-15 13:52:20
*/
public interface CommentService extends IService<Comment> {

    /**
     * 评论
     * @param commentAddDto
     * @return
     */
    BaseResponse<String> commentAdd(CommentAddDto commentAddDto);

    /**
     * 获取评论内容
     *
     * @param id
     * @return
     */
    BaseResponse<List<Comment>> getByPostId(int id);
}
