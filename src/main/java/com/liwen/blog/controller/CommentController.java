package com.liwen.blog.controller;

import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.model.dto.comment.CommentAddDto;
import com.liwen.blog.model.entity.Comment;
import com.liwen.blog.model.vo.CommentVo;
import com.liwen.blog.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论
 */
@RestController("comment")
public class CommentController {
    @Resource
    private CommentService commentService;


    /**
     * 进行评论
     * @param commentAddDto
     * @return
     */
    @PostMapping("save")
    public BaseResponse<String> Comment(@RequestBody CommentAddDto commentAddDto) {
        return commentService.commentAdd(commentAddDto);
    }

    /**
     * 获取评论id
     * @param id
     * @return
     */
    @GetMapping("postcomment")
    public BaseResponse<List<Comment>> getByPostId(int id){
        return commentService.getByPostId(id);
    }
}
