package com.mtons.mblog.web.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.base.storage.StorageFactory;
import com.mtons.mblog.base.utils.FileKit;
import com.mtons.mblog.base.utils.FilePathUtils;
import com.mtons.mblog.base.utils.ImageUtils;
import com.mtons.mblog.modules.pojo.Photo;
import com.mtons.mblog.modules.service.PhotoService;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.posts.UploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName: PhotoController
 * @Auther: Jerry
 * @Date: 2020/5/8 20:53
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Controller("adminPhotoController")
@RequestMapping("/admin/photo")
public class PhotoController extends BaseController {

    @Autowired
    protected StorageFactory storageFactory;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/list")
    public String paging(HttpServletRequest request, ModelMap model) {
//        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
//        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Consts.PAGE_DEFAULT_SIZE);
        IPage<Photo> page = photoService.paging(getPage(request));
        model.put("page", page);
        return "/admin/photo/list";
    }

    @RequestMapping("/view")
    public String view(ModelMap model) {
        return "/admin/photo/view";
    }

    @RequestMapping("/delete")
    @ResponseBody
//	@RequiresPermissions("photo:delete")
    public Result delete(Integer id) {
        Result data = Result.failure("操作失败");
        if (id != null) {
            try {
                photoService.delete(id);
                data = Result.success();
            } catch (Exception e) {
                data = Result.failure(e.getMessage());
            }
        }
        return data;
    }

    @RequestMapping("/save")
//	@RequiresPermissions("channel:update")
    public String save(Photo photo) {
        if (photo != null) {
            photoService.save(photo);

        }
        return "redirect:/admin/photo/list";
    }

    @PostMapping("/upload")
    @ResponseBody
    public UploadController.UploadResult upload(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        UploadController.UploadResult result = new UploadController.UploadResult();

        // 检查空
        if (null == file || file.isEmpty()) {
            return result.error(UploadController.errorInfo.get("NOFILE"));
        }

        String fileName = file.getOriginalFilename();

        // 检查类型
        if (!FileKit.checkFileType(fileName)) {
            return result.error(UploadController.errorInfo.get("TYPE"));
        }

        // 保存图片
        try {
            String ava100 = Consts.avatarPath + getAvaPath(new Random().nextLong(), 240);
            byte[] bytes = ImageUtils.screenshot(file, 640, 640);
            String path = storageFactory.get().writeToStore(bytes, ava100);

            result.ok(UploadController.errorInfo.get("SUCCESS"));
            result.setName(fileName);
            result.setPath(path);
            result.setSize(file.getSize());
        } catch (Exception e) {
            result.error(UploadController.errorInfo.get("UNKNOWN"));
        }
        return result;
    }

    private String getAvaPath(long uid, int size) {
        String base = FilePathUtils.getAvatar(uid);
        return String.format("/%s_%d.jpg", base, size);
    }
}
