/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.site;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mtons.mblog.modules.pojo.Articles;
import com.mtons.mblog.modules.service.PostSearchService;
import com.mtons.mblog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章搜索
 * @author langhsu
 *
 */
@Controller
public class SearchController extends BaseController {
	@Autowired
	private PostSearchService postSearchService;

	@RequestMapping("/search")
	public String search(HttpServletRequest request, String kw, ModelMap model) {
		try {
			if (StringUtils.isNotEmpty(kw)) {
				int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
				int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
				IPage<Articles> page = postSearchService.search(pageNo, pageSize,kw);
				model.put("results", page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("kw", kw);
		return view(Views.SEARCH);
	}
	
}
