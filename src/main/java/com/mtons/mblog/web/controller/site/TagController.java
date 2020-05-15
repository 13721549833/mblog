/**
 *
 */
package com.mtons.mblog.web.controller.site;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mtons.mblog.modules.service.TagService;
import com.mtons.mblog.modules.vo.PostTagVO;
import com.mtons.mblog.modules.vo.TagVO;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 标签
 * @author langhsu
 *
 */
@Controller
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String index(HttpServletRequest request, ModelMap model) {
//        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
//        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Consts.PAGE_DEFAULT_SIZE);
        IPage<TagVO> ipage = tagService.pagingQueryTags(getPage(request), "updated");
        model.put("results", ipage);
        return view(Views.TAG_INDEX);
    }

    @RequestMapping("/tag/{name}")
    public String tag(HttpServletRequest request, @PathVariable String name, ModelMap model) {
//        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
//        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Consts.PAGE_DEFAULT_SIZE);
        IPage<PostTagVO> iPage = tagService.pagingQueryPosts(getPage(request), "weight", name);
        model.put("results", iPage);

        model.put("name", name);
        return view(Views.TAG_VIEW);
    }

}
