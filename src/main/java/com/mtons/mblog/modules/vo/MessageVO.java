package com.mtons.mblog.modules.vo;

import com.mtons.mblog.modules.pojo.Message;

/**
 * @author langhsu on 2015/8/31.
 */
public class MessageVO extends Message {
    private UserVO from;
    // extend
    private PostVO post;

    public UserVO getFrom() {
        return from;
    }

    public void setFrom(UserVO from) {
        this.from = from;
    }

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
