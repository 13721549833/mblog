/**
 *
 */
package com.mtons.mblog.modules.template.directive;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.service.FavoriteService;
import com.mtons.mblog.modules.template.DirectiveHandler;
import com.mtons.mblog.modules.template.TemplateDirective;
import com.mtons.mblog.modules.vo.FavoriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据作者取收藏列表
 *
 * @author landy
 * @since 3.0
 */
@Component
public class UserFavoritesDirective extends TemplateDirective {
    @Autowired
	private FavoriteService favoriteService;

	@Override
	public String getName() {
		return "user_favorites";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        long pageNo = handler.getInteger("pageNo", 1);
        long pageSize = handler.getInteger("pageSize", Consts.PAGE_DEFAULT_SIZE);

        IPage<FavoriteVO> result = favoriteService.pagingByUserId(new Page(pageNo, pageSize), userId);
        handler.put(RESULTS, result).render();
    }

}
