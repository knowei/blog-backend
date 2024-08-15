package com.liwen.blog.service.impl;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.common.ResultUtils;
import com.liwen.blog.mapper.UserMapper;
import com.liwen.blog.model.dto.comment.CommentAddDto;
import com.liwen.blog.model.entity.Comment;
import com.liwen.blog.model.entity.User;
import com.liwen.blog.service.CommentService;
import com.liwen.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zheng
* @description 针对表【tb_comment】的数据库操作Service实现
* @createDate 2024-08-15 13:52:20
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 评论
     * @param commentAddDto
     * @return
     */
    @Override
    public BaseResponse<String> commentAdd(CommentAddDto commentAddDto) {
        String email = commentAddDto.getEmail();
        String username = commentAddDto.getUsername();
        if (email == null || username == null) {
            return new BaseResponse<>(400, null, "邮箱或用户名不能为空");
        }

        //获取用户，判断是否存在
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getEmail, email);
        User user = userMapper.selectOne(qw);
        if (ObjectUtil.isNull(user)) {
            user = new User();
            user.setEmail(email);
            user.setUsername(username);
            userMapper.insert(user);
        }

        //插入评论信息
        Comment comment = new Comment();
        comment.setContent(commentAddDto.getContent());
        comment.setUserId(user.getId());
        comment.setPostId(commentAddDto.getPostId());
        Integer commentId = commentAddDto.getCommentId();
        if (ObjectUtil.isNotNull(commentId)) {
            comment.setParentId(commentId);
            comment.setTreeId(2);
        } else {
            comment.setTreeId(1);
        }
        comment.setCommentLike(0);
        int insert = commentMapper.insert(comment);
        if (insert < 0) {
            return ResultUtils.error(400,"评论失败");
        }
        return ResultUtils.success(null);
    }

    /**
     * 获取评论内容
     *
     * @param id
     * @return
     */
    @Override
    public BaseResponse<List<Comment>> getByPostId(int id) {
        //获取一级评论
        List<Comment> oneComments = commentMapper.getByPostId(null ,id);
        oneComments.forEach(comment -> {
            List<Comment> twoComment = commentMapper.getByPostId(comment.getId(), id);
            comment.setCommList(twoComment);
        });
        return ResultUtils.success(oneComments);
    }
}




