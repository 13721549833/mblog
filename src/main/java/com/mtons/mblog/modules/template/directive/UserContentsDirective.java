/**
 *
 */
package com.mtons.mblog.modules.template.directive;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.template.DirectiveHandler;
import com.mtons.mblog.modules.template.TemplateDirective;
import com.mtons.mblog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据作者取文章列表
 *
 * @author langhsu
 *
 */
@Component
public class UserContentsDirective extends TemplateDirective {

    @Autowired
    private PostService postService;

	@Override
	public String getName() {
		return "user_contents";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        long pageNo = handler.getInteger("pageNo", 1);
        long pageSize = handler.getInteger("pageSize", Consts.PAGE_DEFAULT_SIZE);

        IPage<PostVO> result = postService.pagingByAuthorId(new Page(pageNo, pageSize), userId);
        handler.put(RESULTS, result).render();
    }

}
