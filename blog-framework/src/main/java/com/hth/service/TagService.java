package com.hth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hth.domain.Dto.TagListDto;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Tag;
import com.hth.domain.vo.PageVo;
import com.hth.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-03-10 14:45:51
 */
public interface TagService extends IService<Tag> {

    List<TagVo> listAllTag();

    ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto);

}
