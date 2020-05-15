package com.mtons.mblog.modules.vo;

import java.io.Serializable;

/**
 * @author langhsu
 */
public class BadgesCount implements Serializable {

    private static final long serialVersionUID = 2649716968676862858L;

    private int messages; // 消息数量

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
