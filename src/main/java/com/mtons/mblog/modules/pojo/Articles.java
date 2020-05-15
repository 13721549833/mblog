package com.mtons.mblog.modules.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Article
 * @Auther: Jerry
 * @Date: 2020/4/20 10:32
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "es_article_index", type = "article")
public class Articles implements Serializable {

    private static final long serialVersionUID = -728655685413761417L;

    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 状态
     */
    private int status;
    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store=true)
    private String title;

    /**
     * 内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store=true)
    private String summary;
    /**
     * 标签
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store=true)
    private String tags;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;
    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 作者
     */
    private Object author;

    /**
     * 分组/模块
     */
    private int channelId;

    /**
     * 分组/模块
     */
    private Object channel;

    /**
     * 收藏数
     */
    private int favors;
    /**
     * 评论数
     */
    private int comments;
    /**
     * 阅读数
     */
    private int views;
    /**
     * 推荐状态
     */
    private int featured;
    /**
     * 预览图
     */
    private String thumbnail;
}
