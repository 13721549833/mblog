/**
 *
 */
package com.mtons.mblog.modules.template.directive;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.service.MessageService;
import com.mtons.mblog.modules.template.DirectiveHandler;
import com.mtons.mblog.modules.template.TemplateDirective;
import com.mtons.mblog.modules.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 查询用户消息列表
 *
 * @author landy
 * @since 3.0
 */
@Component
public class UserMessagesDirective extends TemplateDirective {
    @Autowired
	private MessageService messageService;

	@Override
	public String getName() {
		return "user_messages";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        long pageNo = handler.getInteger("pageNo", 1);
        long pageSize = handler.getInteger("pageSize", Consts.PAGE_DEFAULT_SIZE);

        IPage<MessageVO> result = messageService.pagingByUserId(new Page(pageNo, pageSize), userId);
        handler.put(RESULTS, result).render();
    }

}
