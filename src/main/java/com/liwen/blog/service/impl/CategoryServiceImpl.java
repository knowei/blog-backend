package com.liwen.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.model.entity.Category;
import com.liwen.blog.service.CategoryService;
import com.liwen.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【tb_category】的数据库操作Service实现
* @createDate 2024-08-13 10:50:21
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




