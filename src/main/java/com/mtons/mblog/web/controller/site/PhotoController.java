package com.mtons.mblog.web.controller.site;

import com.mtons.mblog.modules.service.PhotoService;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PhotoController
 * @Auther: Jerry
 * @Date: 2020/5/8 17:35
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Controller
public class PhotoController extends BaseController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/photos")
    public String index(ModelMap model) {
        List<Map<String, Object>> results = photoService.queryPhotoList();
        model.put("results", results);
        return view(Views.PHOTO_VIEW);
    }
}
