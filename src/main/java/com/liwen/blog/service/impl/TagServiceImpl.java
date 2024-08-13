package com.liwen.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.model.entity.Tag;
import com.liwen.blog.service.TagService;
import com.liwen.blog.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【tb_tag】的数据库操作Service实现
* @createDate 2024-08-13 11:04:22
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




