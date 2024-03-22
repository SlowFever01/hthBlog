package com.hth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hth.domain.entity.User;
import org.springframework.stereotype.Service;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-26 20:17:48
 */
@Service
public interface UserMapper extends BaseMapper<User> {

}
