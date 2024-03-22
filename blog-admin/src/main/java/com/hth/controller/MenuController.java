package com.hth.controller;

import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Menu;
import com.hth.domain.vo.MenuTreeVo;
import com.hth.domain.vo.MenuVo;
import com.hth.domain.vo.RoleMenuTreeSelectVo;
import com.hth.enums.AppHttpCodeEnum;
import com.hth.service.MenuService;
import com.hth.utils.BeanCopyUtils;
import com.hth.utils.SystemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @param menu
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(Menu menu){
        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus,MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    /**
     * 新增角色-获取菜单下拉树列表
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult treeSelect(){
        //复用之前的selectMenuList方法，方法需要参数，可以用来进行条件查询，而这个方法不需要条件，所以直接new Menu()传入
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(menuTreeVos);

    }

    //修改角色-根据角色id查询对应角色菜单列表树

    @GetMapping("/roleMenuTreeselect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable Long roleId){
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys,menuTreeVos);
        return ResponseResult.okResult(vo);

    }


    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    //根据菜单id查询对应的权限菜单
    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable Long id){
        return ResponseResult.okResult(menuService.getById(id));
    }


    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Menu menu){
        //不能把父菜单设置为当前菜单
        if(menu.getId().equals(menu.getParentId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"修改菜单"+menu.getMenuName()+"失败，不能把父菜单设置为当前菜单");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        if(menuService.hasChild(id)){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"存在子菜单不允许删除");
        }
        menuService.removeById(id);
        return ResponseResult.okResult();
    }


}
