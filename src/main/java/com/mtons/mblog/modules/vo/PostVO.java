/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.mtons.mblog.modules.pojo.Channel;
import com.mtons.mblog.modules.pojo.Post;
import com.mtons.mblog.modules.pojo.PostAttribute;
import lombok.Data;

import java.io.Serializable;

/**
 * @author langhsu
 * 
 */
@Data
public class PostVO extends Post implements Serializable {

	private static final long serialVersionUID = -185831968770781690L;
	private String editor;
	private String content;

	private UserVO author;
	private Channel channel;
	
	@JSONField(serialize = false)
	private PostAttribute attribute;

}
