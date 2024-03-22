package com.hth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Category;
import com.hth.domain.vo.CategoryVo;
import com.hth.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-10-26 11:53:29
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    //分页查询分类列表
    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}
