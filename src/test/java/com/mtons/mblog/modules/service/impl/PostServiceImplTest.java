package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.BootApplication;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.mapper.PostMapper;
import com.mtons.mblog.modules.pojo.Articles;
import com.mtons.mblog.modules.pojo.Post;
import com.mtons.mblog.modules.repository.ArticlesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * post service test
 *
 * @author saxing 2019/4/5 17:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class PostServiceImplTest {

    @Autowired
    PostMapper postMapper;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Test
    public void cleanPostPic() {
    }

    @Test
    public void test(){
        QueryWrapper<Post> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        queryWrapper.eq("channel_id", 2);
        String[] orders = BeanMapUtil.postOrder("newest");
        queryWrapper.orderByDesc(orders[0], orders[1]);

        List<Post> postList = postMapper.selectPage(new Page<>(1, 100), queryWrapper).getRecords();
        List<Articles> articlesList = new ArrayList<>();
        for (Post po : postList) {
            Articles articles = new Articles();
            BeanMapUtil.post2Articles(po);
            articlesList.add(articles);
            articlesRepository.save(articles);
        }
//        articlesRepository.saveAll(articlesList);
    }
}