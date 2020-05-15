package com.mtons.mblog.modules.vo;
import com.mtons.mblog.modules.pojo.PostTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : langhsu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostTagVO extends PostTag implements Serializable {

    private static final long serialVersionUID = 2855051789915602809L;

    private PostVO post;
}
