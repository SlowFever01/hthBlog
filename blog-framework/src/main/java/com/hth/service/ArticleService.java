package com.hth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Article;
import com.hth.domain.Dto.AddArticleDto;
import com.hth.domain.vo.ArticleVo;
import com.hth.domain.vo.PageVo;

public interface ArticleService extends IService<Article> {

    //文章列表
    ResponseResult hotArticleList();
    //分类查询文章列表
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
    //根据id查询文章详情
    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto article);
    //管理后台(文章管理)-分页查询文章
    PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    ArticleVo getInfo(Long id);

    void updateArticle(ArticleVo articleVo);
}
