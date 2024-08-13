package com.liwen.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.model.dto.post.PostAddDto;
import com.liwen.blog.model.dto.post.PostQueryDto;
import com.liwen.blog.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwen.blog.model.vo.PostVO;

import java.util.List;

/**
* @author zheng
* @description 针对表【tb_post】的数据库操作Service
* @createDate 2024-08-13 10:05:01
*/
public interface PostService extends IService<Post> {

    /**
     * 保存文章
     * @param post
     * @return
     */
    boolean savePost(PostAddDto post);

    /**
     * 根据 id 获取文章
     * @param id
     * @return
     */
    PostVO getById(Integer id);

    /**
     * 根据条件获取文章列表
     * @param postQueryDto
     * @return
     */
    List<PostVO> listPost(PostQueryDto postQueryDto);

    /**
     * 分页获取文章列表
     * @param postQueryDto
     * @return
     */
    Page<PostVO> listPostByPage(PostQueryDto postQueryDto);
}
