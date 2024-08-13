package com.liwen.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.common.DeleteRequest;
import com.liwen.blog.common.ErrorCode;
import com.liwen.blog.common.ResultUtils;
import com.liwen.blog.exception.BusinessException;
import com.liwen.blog.model.dto.category.CategoryQueryDto;
import com.liwen.blog.model.entity.Category;
import com.liwen.blog.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    // region 增删改查

    /**
     * 创建文章
     *
     * @param category
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody Category category, HttpServletRequest request) {
        if (category == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = categoryService.save(category);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(Long.valueOf(category.getId()));
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
        boolean b = categoryService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新文章
     *
     * @param category
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody Category category, HttpServletRequest request) {
        if (category == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Category post = new Category();
        BeanUtils.copyProperties(category, post);
        boolean result = categoryService.updateById(post);
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
    public BaseResponse<Category> getPostById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Category category = categoryService.getById(id);
        return ResultUtils.success(category);
    }

    /**
     * 获取文章列表
     *
     * @param category
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Category>> listPost(Category category, HttpServletRequest request) {
        QueryWrapper<Category> qw = new QueryWrapper<>();
        List<Category> list = categoryService.list(qw);
        return ResultUtils.success(list);
    }

    /**
     * 分页获取文章列表
     *
     * @param categoryQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Category>> listPostByPage(CategoryQueryDto categoryQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        Category categoryQuery = new Category();
        if (categoryQueryRequest != null) {
            BeanUtils.copyProperties(categoryQueryRequest, categoryQuery);
            current = categoryQueryRequest.getCurrent();
            size = categoryQueryRequest.getPageSize();
        }
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>(categoryQuery);
        Page<Category>postPage = categoryService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(postPage);
    }

    // endregion
}
