package com.liwen.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.common.DeleteRequest;
import com.liwen.blog.common.ErrorCode;
import com.liwen.blog.common.ResultUtils;
import com.liwen.blog.exception.BusinessException;
import com.liwen.blog.model.dto.tag.TagQueryDto;
import com.liwen.blog.model.entity.Tag;
import com.liwen.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 *
 * @author knowei
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;


    // region 增删改查

    /**
     * 创建文章
     *
     * @param tag
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody Tag tag, HttpServletRequest request) {
        if (tag == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = tagService.save(tag);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(Long.valueOf(tag.getId()));
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
        boolean b = tagService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新文章
     *
     * @param tag
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody Tag tag, HttpServletRequest request) {
        if (tag == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tag post = new Tag();
        BeanUtils.copyProperties(tag, post);
        boolean result = tagService.updateById(post);
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
    public BaseResponse<Tag> getPostById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tag tag = tagService.getById(id);
        return ResultUtils.success(tag);
    }

    /**
     * 获取文章列表
     *
     * @param tag
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Tag>> listPost(Tag tag, HttpServletRequest request) {
        QueryWrapper<Tag> qw = new QueryWrapper<>();
        List<Tag> list = tagService.list(qw);
        return ResultUtils.success(list);
    }

    /**
     * 分页获取文章列表
     *
     * @param tagQueryDto
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Tag>> listPostByPage(TagQueryDto tagQueryDto, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        Tag tagQuery = new Tag();
        if (tagQueryDto != null) {
            BeanUtils.copyProperties(tagQueryDto, tagQuery);
            current = tagQueryDto.getCurrent();
            size = tagQueryDto.getPageSize();
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>(tagQuery);
        Page<Tag>postPage = tagService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(postPage);
    }

    // endregion
}
