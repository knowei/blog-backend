package cn.uniqueww.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.uniqueww.project.model.entity.Post;

/**
 * @author knowei
 * @description 针对表【post(帖子)】的数据库操作Service
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add 是否为创建校验
     */
    void validPost(Post post, boolean add);
}
