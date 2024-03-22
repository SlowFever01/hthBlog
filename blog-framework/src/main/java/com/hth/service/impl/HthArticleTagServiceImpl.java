package com.hth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hth.domain.entity.HthArticleTag;
import com.hth.mapper.HthArticleTagMapper;
import com.hth.service.HthArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(HthArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 13:45:19
 */
@Service("hthArticleTagService")
public class HthArticleTagServiceImpl extends ServiceImpl<HthArticleTagMapper, HthArticleTag> implements HthArticleTagService {

}
