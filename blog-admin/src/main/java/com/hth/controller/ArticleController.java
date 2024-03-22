package com.hth.controller;

import com.hth.domain.ResponseResult;
import com.hth.domain.Dto.AddArticleDto;
import com.hth.domain.entity.Article;
import com.hth.domain.vo.ArticleVo;
import com.hth.domain.vo.PageVo;
import com.hth.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto article){
        return articleService.addArticle(article);
    }

    /**
     * 分页查询博客文章
     * @param article
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize)
    {
        PageVo pageVo = articleService.selectArticlePage(article,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 查询文章详情接口
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    /**
     * 更新文章接口
     * @param articleVo
     * @return
     */
    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleVo articleVo){
        articleService.updateArticle(articleVo);
        return ResponseResult.okResult();
    }

    /**
     * 删除文章接口
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
