package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.pojo.Articles;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName: ArticlesRepository
 * @Auther: Jerry
 * @Date: 2020/4/20 11:32
 * @Desctiption: 文章搜索
 * @Version: 1.0
 */
public interface ArticlesRepository extends ElasticsearchRepository<Articles, Long> {
}
