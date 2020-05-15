package com.mtons.mblog.modules.service.impl;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.BootApplication;
import com.mtons.mblog.modules.service.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class PhotoServiceImplTest {

    @Autowired
    private PhotoService photoService;

    @Test
    public void test(){
        List<Map<String, Object>> map = photoService.queryPhotoList();
        System.out.println(JSON.toJSONString(map));
    }
}
