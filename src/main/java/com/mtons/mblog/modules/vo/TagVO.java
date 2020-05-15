package com.mtons.mblog.modules.vo;

import com.mtons.mblog.modules.pojo.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : langhsu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagVO extends Tag implements Serializable {

    private static final long serialVersionUID = 244275656416679297L;

    private PostVO post;
}
