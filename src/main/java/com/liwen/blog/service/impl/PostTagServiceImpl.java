package com.liwen.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.model.entity.PostTag;
import com.liwen.blog.service.PostTagService;
import com.liwen.blog.mapper.PostTagMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【tb_post_tag】的数据库操作Service实现
* @createDate 2024-08-13 11:07:53
*/
@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag>
    implements PostTagService{

}




