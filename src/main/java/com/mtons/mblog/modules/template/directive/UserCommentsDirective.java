/**
 *
 */
package com.mtons.mblog.modules.template.directive;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.service.CommentService;
import com.mtons.mblog.modules.template.DirectiveHandler;
import com.mtons.mblog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据作者取评论列表
 *
 * @author landy
 * @since 3.0
 */
@Component
public class UserCommentsDirective extends TemplateDirective {

    @Autowired
    private CommentService commentService;

	@Override
	public String getName() {
		return "user_comments";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        int pageNo = handler.getInteger("pageNo", 1);
        int pageSize = handler.getInteger("pageSize", Consts.PAGE_DEFAULT_SIZE);
        IPage result = commentService.pagingByAuthorId(new Page(pageNo, pageSize), userId);
        handler.put(RESULTS, result).render();
    }

}
