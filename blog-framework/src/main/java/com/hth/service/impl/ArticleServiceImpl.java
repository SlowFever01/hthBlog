package com.hth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hth.constants.SystemConstants;
import com.hth.domain.Dto.AddArticleDto;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Article;
import com.hth.domain.entity.Category;
import com.hth.domain.entity.HthArticleTag;
import com.hth.domain.entity.Tag;
import com.hth.domain.vo.*;
import com.hth.mapper.ArticleMapper;
import com.hth.service.ArticleService;
import com.hth.service.CategoryService;
import com.hth.service.HthArticleTagService;
import com.hth.utils.BeanCopyUtils;
import com.hth.utils.RedisCache;
import com.hth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
//ServiceImpl是mybatisPlus官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private HthArticleTagService articleTagService;
    /*
    * 热门文章
    */
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回，把所有查询条件写在queryWrapper里面
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条，当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page(SystemConstants.ARTICLE_STATUS_CURRENT,SystemConstants.ARTICLE_STATUS_SIZE);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        //bean拷贝
    /*    List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo articleVo = new HotArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVos.add(articleVo);
        }*/

        //使用工具类拷贝
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    /*
    * 分页查询文章列表
    */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        //articleWrapper 是用于构建数据库查询条件的对象，它并不包含查询结果本身，而是用于生成查询语句并获取匹配条件的数据库记录。
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId就要查询时和传入的相同
        articleWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        articleWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop降序排序
        articleWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize); //指定分页查询的页码和每页记录数
        page(page,articleWrapper); //通过 page(page, articleWrapper) 方法执行分页查询，将符合条件的文章数据放入 page 对象中。

        //查询categoryName
       List<Article> articles = page.getRecords();
       /*
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
*/
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<articleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), articleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }


    /*
     * 文章详情接口
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        //如果根据分类id查询的到分类名，那么就把查询到的值设置给articleDetailVo实体类的categoryName字段
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中的浏览量，对应文章id的viewCount浏览量。article:viewCount是ViewCountRunner类里面写的
        //用户每从mysql根据文章id查询一次浏览量，那么redis的浏览量就增加1
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    /**
     * 添加博文
     * @param articleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        List<HthArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new HthArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    /**
     * //管理后台(文章管理)-分页查询文章
     * @param article
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(article.getTitle()),Article::getTitle, article.getTitle());
        queryWrapper.like(StringUtils.hasText(article.getSummary()),Article::getSummary, article.getSummary());

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(page.getRecords());

        return pageVo;
    }

    @Override
    public ArticleVo getInfo(Long id) {
        Article article = getById(id);
        LambdaQueryWrapper<HthArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HthArticleTag::getArticleId,article.getId());
        List<HthArticleTag> hthArticleTags = articleTagService.list(queryWrapper);
        List<Long> tags = hthArticleTags.stream().map(HthArticleTag::getTagId).collect(Collectors.toList());

        ArticleVo articleVo = BeanCopyUtils.copyBean(article,ArticleVo.class);
        articleVo.setTags(tags);
        return articleVo;
    }

    @Override
    public void updateArticle(ArticleVo articleVo) {
        Article article = BeanCopyUtils.copyBean(articleVo,Article.class);
        //更新博客信息
        updateById(article);
        //删除原有的标签和博客的关联
        LambdaQueryWrapper<HthArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HthArticleTag::getArticleId,article.getId());
        articleTagService.remove(queryWrapper);
        //添加新的博客和标签的关联信息
        List<HthArticleTag> hthArticleTags = articleVo.getTags().stream()
                .map(tagId->new HthArticleTag(articleVo.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(hthArticleTags);
    }

}
