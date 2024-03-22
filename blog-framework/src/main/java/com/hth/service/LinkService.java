package com.hth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Link;
import com.hth.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-10-26 19:33:25
 */
public interface LinkService extends IService<Link> {
        ResponseResult getAllLink();

        //分页查询友链
        PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
