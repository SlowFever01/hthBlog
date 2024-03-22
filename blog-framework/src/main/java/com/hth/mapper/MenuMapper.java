package com.hth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hth.domain.entity.Menu;
import com.hth.domain.vo.MenuVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:39:51
 */
public interface MenuMapper extends BaseMapper<Menu> {
    //查询普通用户的权限信息
    List<String> selectPermsByUserId(Long id);
    //查询超级管理员的路由信息(权限菜单)
    List<MenuVo> selectAllRouterMenu();

    //查询普通用户的路由信息(权限菜单)
    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);

    //修改角色-根据角色id查询对应角色菜单列表树
    List<Long> selectMenuListByRoleId(Long roleId);
}
