package com.liwen.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.common.DeleteRequest;
import com.liwen.blog.common.ErrorCode;
import com.liwen.blog.common.ResultUtils;
import com.liwen.blog.exception.BusinessException;
import com.liwen.blog.model.dto.post.PostAddDto;
import com.liwen.blog.model.dto.post.PostQueryDto;
import com.liwen.blog.model.dto.post.PostUpdateDto;
import com.liwen.blog.model.entity.Post;
import com.liwen.blog.model.vo.PostVO;
import com.liwen.blog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章接口
 *
 * @author knowei
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;


    // region 增删改查

    /**
     * 创建文章
     *
     * @param postAddDto
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addPost(@RequestBody PostAddDto postAddDto, HttpServletRequest request) {
        if (postAddDto == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result =  postService.savePost(postAddDto);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    /**
     * 删除文章
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = postService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新文章
     *
     * @param postUpdateDto
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateDto postUpdateDto, HttpServletRequest request) {
        if (postUpdateDto == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateDto, post);
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取文章
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<PostVO> getPostById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        PostVO postVO = postService.getById(id);
        return ResultUtils.success(postVO);
    }

    /**
     * 获取文章列表
     *
     * @param postQueryDto
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<PostVO>> listPost(PostQueryDto postQueryDto, HttpServletRequest request) {
        List<PostVO> userVOList = postService.listPost(postQueryDto);
        return ResultUtils.success(userVOList);
    }

    /**
     * 分页获取文章列表
     *
     * @param postQueryDto
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<PostVO>> listPostByPage(PostQueryDto postQueryDto, HttpServletRequest request) {

        Page<PostVO> postVOPage = postService.listPostByPage(postQueryDto);
        return ResultUtils.success(postVOPage);
    }

    // endregion
}
