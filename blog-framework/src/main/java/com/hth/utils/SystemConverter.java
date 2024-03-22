package com.hth.utils;

import com.hth.domain.entity.Menu;
import com.hth.domain.vo.MenuTreeVo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//新增角色-获取菜单下拉树列表
public class SystemConverter {
        public static List<MenuTreeVo> buildMenuSelectTree(List<Menu> menus){
                List<MenuTreeVo> menuTreeVos = menus.stream()
                        .map(menu -> new MenuTreeVo(menu.getId(),menu.getMenuName(), menu.getParentId(),null))
                        .collect(Collectors.toList());
                List<MenuTreeVo> options = menuTreeVos.stream()
                        .filter(o->o.getParentId().equals(0L))
                        .map(o->o.setChildren(getChildrenList(menuTreeVos,o)))
                        .collect(Collectors.toList());
                return options;
        }

        /**
         *  得到子节点列表
         * @param menuTreeVos
         * @param option
         * @return
         */
        private static List<MenuTreeVo> getChildrenList(List<MenuTreeVo> menuTreeVos, MenuTreeVo option) {
                List<MenuTreeVo> options = menuTreeVos.stream()
                        .filter(o-> Objects.equals(o.getParentId(),option.getId()))
                        .map(o->o.setChildren(getChildrenList(menuTreeVos, o)))
                        .collect(Collectors.toList());
                return options;
        }
}
