package com.hth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hth.constants.SystemConstants;
import com.hth.domain.entity.Menu;
import com.hth.domain.vo.MenuVo;
import com.hth.mapper.MenuMapper;
import com.hth.service.MenuService;
import com.hth.utils.BeanCopyUtils;
import com.hth.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:39:52
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
/*        if(SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Menu::getMenuType, SystemConstants.TYPE_MENU);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }*/
        //否者返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<MenuVo> menuVos = null;
        //判断是否是管理员
//        if(SecurityUtils.isAdmin()){
//            //如果是 获取所有符合要求的Menu
//            menuVos = menuMapper.selectAllRouterMenu();
//        }else{
            //否则获取当前用户所具有的Menu
            menuVos = menuMapper.selectRouterMenuTreeByUserId(userId);
//        }
        //构建tree
        //先找出第一层的菜单，然后去找他们的子菜单设置到children属性中
        List<MenuVo> menuTree = builderMenuTree(menuVos,0L);
        return menuTree;
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(menu.getMenuName()),Menu::getMenuName,menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()),Menu::getStatus,menu.getStatus());
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);

        List<Menu> menuList = list(queryWrapper);
        return menuList;
    }

    @Override
    public boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,menuId);
        return count(queryWrapper)!=0;
    }

    /**
     * 修改角色-根据角色id查询对应角色菜单列表树
     * @param roleId
     * @return
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuListByRoleId(roleId);
    }

    private List<MenuVo> builderMenuTree(List<MenuVo> menuVos, Long parentId) {
        List<MenuVo> menuTree = menuVos.stream()
                .filter(menuVo -> menuVo.getParentId().equals(parentId))
                .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menuVos)))
                .collect(Collectors.toList());
        return menuTree;

    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menuVo
     * @param menuVos
     * @return
     */
    private List<MenuVo> getChildren(MenuVo menuVo, List<MenuVo> menuVos) {
        List<MenuVo> childrenList = menuVos.stream()
                .filter(menu -> menu.getParentId().equals(menuVo.getId()))
                .map(menu->menu.setChildren(getChildren(menu,menuVos)))
                .collect(Collectors.toList());
        return childrenList;
    }



}
