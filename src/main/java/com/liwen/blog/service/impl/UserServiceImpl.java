package com.liwen.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.model.entity.User;
import com.liwen.blog.service.UserService;
import com.liwen.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【tb_user】的数据库操作Service实现
* @createDate 2024-08-15 13:58:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




