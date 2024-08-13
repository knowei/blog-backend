package com.liwen.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwen.blog.common.BaseResponse;
import com.liwen.blog.common.ErrorCode;
import com.liwen.blog.exception.BusinessException;
import com.liwen.blog.mapper.PostTagMapper;
import com.liwen.blog.model.dto.post.PostAddDto;
import com.liwen.blog.model.dto.post.PostQueryDto;
import com.liwen.blog.model.entity.Post;
import com.liwen.blog.model.entity.PostTag;
import com.liwen.blog.model.vo.PostVO;
import com.liwen.blog.service.PostService;
import com.liwen.blog.mapper.PostMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zheng
* @description 针对表【tb_post】的数据库操作Service实现
* @createDate 2024-08-13 10:05:01
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostTagMapper postTagMapper;

    /**
     * 保存文章
     * @param postAddDto
     * @return
     */
    @Override
    public boolean savePost(PostAddDto postAddDto) {
        Post post = new Post();
        BeanUtils.copyProperties(postAddDto, post);
        int insert = postMapper.insert(post);
        //插入文章-标签表
        List<Integer> postTags = postAddDto.getTags();
        for (Integer postTag : postTags) {
            PostTag postTagE = new PostTag();
            postTagE.setPostId(post.getId());
            postTagE.setTagId(postTag);
            postTagMapper.insert(postTagE);
        }

        if (insert < 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return true;
    }

    /**
     * 根据 id 获取文章
     * @param id
     * @return
     */
    @Override
    public PostVO getById(Integer id) {
        Post post = postMapper.selectById(id);
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);

        List<PostTag> postTags = postTagMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId())
                .select(PostTag::getTagId));
        List<Integer> tags = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
        postVO.setTags(tags);
        return postVO;
    }

    /**
     * 根据条件获取文章列表
     * @param postQueryDto
     * @return
     */
    @Override
    public List<PostVO> listPost(PostQueryDto postQueryDto) {
        Post postQuery = new Post();
        BeanUtils.copyProperties(postQueryDto, postQuery);
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>(postQuery);

        List<Post> postList = postMapper.selectList(queryWrapper);

        //获取文章列表
        List<PostVO> userVOList = postList.stream().map(post -> {
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(post, postVO);

            //获取文章对应的标签id
            List<PostTag> postTags = postTagMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId())
                    .select(PostTag::getTagId));
            List<Integer> list = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
            postVO.setTags(list);

            return postVO;
        }).collect(Collectors.toList());

        return userVOList;
    }

    /**
     * 分页获取文章列表
     * @param postQueryDto
     * @return
     */
    @Override
    public Page<PostVO> listPostByPage(PostQueryDto postQueryDto) {
        long current = 1;
        long size = 10;
        Post postQuery = new Post();
        if (postQueryDto != null) {
            BeanUtils.copyProperties(postQueryDto, postQuery);
            current = postQueryDto.getCurrent();
            size = postQueryDto.getPageSize();
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>(postQuery);
        Page<Post>postPage = postMapper.selectPage(new Page<>(current, size), queryWrapper);
        Page<PostVO> postVOPage = new PageDTO<>(postPage.getCurrent(), postPage.getSize(), postPage.getTotal());
        List<PostVO> postVOList = postPage.getRecords().stream().map(post -> {
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(post, postVO);

            //获取文章对应的标签id
            List<PostTag> postTags = postTagMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId())
                    .select(PostTag::getTagId));
            List<Integer> list = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
            postVO.setTags(list);


            return postVO;
        }).collect(Collectors.toList());
        postVOPage.setRecords(postVOList);
        return postVOPage;
    }
}




