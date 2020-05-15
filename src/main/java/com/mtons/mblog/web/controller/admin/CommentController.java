/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.modules.service.CommentService;
import com.mtons.mblog.modules.vo.CommentVO;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author langhsu
 *
 */
@Controller("adminCommentController")
@RequestMapping("/admin/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, ModelMap model) {
//        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
//        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Consts.PAGE_DEFAULT_SIZE);
		IPage<CommentVO> page = commentService.paging4Admin(getPage(request));
		model.put("page", page);
		return "/admin/comment/list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("id") List<Long> id) {
		Result data = Result.failure("操作失败");
		if (id != null) {
			try {
				commentService.deleteBatch(id);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
}
