package com.hth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hth.constants.SystemConstants;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Role;
import com.hth.domain.entity.RoleMenu;
import com.hth.domain.vo.PageVo;
import com.hth.enums.AppHttpCodeEnum;
import com.hth.mapper.RoleMapper;
import com.hth.service.RoleMenuService;
import com.hth.service.RoleService;
import com.hth.utils.BeanCopyUtils;
import com.hth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:41:44
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;
    /**
     * //查询用户的角色信息
     * @param id
     * @return
     */
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    /**
     * 查询角色列表
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(role.getStatus()),Role::getStatus,role.getStatus());
        queryWrapper.like(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName());
        queryWrapper.orderByAsc(Role::getRoleSort);


        Page<Role> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        page(page,queryWrapper);

        //转换成VO
        List<Role> roles = page.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setRows(roles);
        pageVo.setTotal(page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 新增角色
     * @param role
     */
    @Override
    public void insertRole(Role role) {
        save(role);
        System.out.println(role.getId());
        if(role.getMenuIds()!=null&&role.getMenuIds().length>0){
            insertRoleMenu(role);
        }
    }

    /**
     * 修改角色-保存修改好的角色信息
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        updateById(role);
        roleMenuService.deleteRoleMenuByRoleId(role.getId());
        insertRoleMenu(role);
    }

    @Override
    public List<Role> selectRoleAll() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, SystemConstants.STATUS_NORMAL);
        return list(queryWrapper);
    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        return getBaseMapper().selectRoleIdByUserId(userId);
    }

    private void insertRoleMenu(Role role) {
        List<RoleMenu> roleMenuList = Arrays.stream(role.getMenuIds())
                .map(menuId->new RoleMenu(role.getId(),menuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
    }


}
