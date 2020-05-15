package com.mtons.mblog.base.utils;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.pojo.*;
import com.mtons.mblog.modules.vo.*;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName: BeanMapNewUtils
 * @Auther: Jerry
 * @Date: 2020/4/9 14:50
 * @Desctiption: TODO
 * @Version: 1.0
 */
public class BeanMapUtil {

    private static String[] USER_IGNORE = new String[]{"password", "extend", "roles"};
    public static UserVO copy(User user){
        if (user == null) {
            return null;
        }
        UserVO ret = new UserVO();
        BeanUtils.copyProperties(user, ret, USER_IGNORE);
        return ret;
    }

    public static CommentVO copy(Comment po) {
        CommentVO ret = new CommentVO();
        BeanUtils.copyProperties(po, ret);
        return ret;
    }

    public static PostVO copy(Post po){
        PostVO d = new PostVO();
        BeanUtils.copyProperties(po, d);
        return d;
    }

    public static TagVO copy(Tag po) {
        TagVO ret = new TagVO();
        BeanUtils.copyProperties(po, ret);
        return ret;
    }

    public static PostTagVO copy(PostTag po) {
        PostTagVO ret = new PostTagVO();
        BeanUtils.copyProperties(po, ret);
        return ret;
    }

    public static FavoriteVO copy(Favorite po) {
        FavoriteVO favoriteVO = new FavoriteVO();
        BeanUtils.copyProperties(po, favoriteVO);
        return favoriteVO;
    }

    public static MessageVO copy(Message po) {
        MessageVO ret = new MessageVO();
        BeanUtils.copyProperties(po, ret);
        return ret;
    }

    public static AccountProfile copyPassport(User po) {
        AccountProfile passport = new AccountProfile(po.getId(), po.getUsername());
        passport.setName(po.getName());
        passport.setEmail(po.getEmail());
        passport.setAvatar(po.getAvatar());
        passport.setLastLogin(po.getLastLogin());
        passport.setStatus(po.getStatus());
        return passport;
    }

    public static String[] postOrder(String order) {
        String[] orders;
        switch (order) {
            case Consts.order.HOTTEST:
                orders = new String[]{"comments", "views", "created"};
                break;
            case Consts.order.FAVOR:
                orders = new String[]{"favors", "created"};
                break;
            default:
                orders = new String[]{"weight", "created"};
                break;
        }
        return orders;
    }

    public static Articles post2Articles(Post post){
        Articles articles = new Articles();
        articles.setId(post.getId());
        articles.setFavors(post.getFavors());
        articles.setComments(post.getComments());
        articles.setViews(post.getViews());
        articles.setFeatured(post.getFeatured());
        articles.setThumbnail(post.getThumbnail());
        articles.setStatus(post.getStatus());
        articles.setTitle(post.getTitle());
        articles.setSummary(post.getSummary());
        articles.setTags(post.getTags());
        articles.setCreated(post.getCreated());
        return articles;
    }
}
