package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.vo.PostTagVO;
import com.mtons.mblog.modules.vo.TagVO;

/**
 * @ClassName: TagNewService
 * @Auther: Jerry
 * @Date: 2020/4/8 11:45
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface TagService {

    /**
     * 批量更新
     *
     * @param names        标签名
     * @param latestPostId 最新帖子Id
     */
    void batchUpdate(String names, long latestPostId);

    IPage<TagVO> pagingQueryTags(Page page, String order);

    IPage<PostTagVO> pagingQueryPosts(Page page, String order, String name);

    void deteleByPostId(long postId);
}
