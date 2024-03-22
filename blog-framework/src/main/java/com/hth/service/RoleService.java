package com.hth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:41:44
 */
public interface RoleService extends IService<Role> {
     List<String> selectRoleKeyByUserId(Long id);

     //查询角色列表
     ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);

     //新增角色
     void insertRole(Role role);

     //修改角色-保存修改好的角色信息
     void updateRole(Role role);

     //新增用户-①查询角色列表接口
     List<Role> selectRoleAll();

     //修改用户-①根据id查询用户信息
     List<Long> selectRoleIdByUserId(Long userId);
}
