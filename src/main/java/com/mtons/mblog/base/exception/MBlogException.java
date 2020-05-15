package com.mtons.mblog.base.exception;

/**
 * MBlog异常
 *
 * @author xiaomi
 * @date 2020/03/27
 */
public class MBlogException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MBlogException(String message) {
        super(message);
    }

    public MBlogException(Throwable cause) {
        super(cause);
    }

    public MBlogException(String message, Throwable cause) {
        super(message, cause);
    }
}