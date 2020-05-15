package com.mtons.mblog.modules.mapper;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.BootApplication;
import com.mtons.mblog.modules.pojo.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * pic repo test
 *
 * @author saxing 2019/4/5 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Test
    public void find0Before() {
        List<Post> postList = postMapper.selectList(new QueryWrapper<Post>().eq("status", "0"));
        System.out.println(JSON.toJSONString(postList));
    }

    @Test
    public void update(){
        List<String> ids = Arrays.asList("3LMKU9CL5VLAM3UHU5G96EMTO1","245QMNRSH5NSFITJF9ITB3U6D2");
        resourceMapper.updateAmount(ids, 1);
//        resourceMapper.selectByIds(ids);
    }
}