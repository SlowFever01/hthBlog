package com.hth.controller;

import com.hth.domain.ResponseResult;
import com.hth.domain.entity.Role;
import com.hth.domain.entity.User;
import com.hth.domain.vo.UserInfoAndRoleIdsVo;
import com.hth.enums.AppHttpCodeEnum;
import com.hth.exception.SystemException;
import com.hth.service.RoleService;
import com.hth.service.UserService;
import com.hth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询用户列表
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserPage(user,pageNum,pageSize);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody User user) {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!userService.checkUserNameUnique(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!userService.checkPhoneUnique(user)){
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (!userService.checkEmailUnique(user)){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        return userService.addUser(user);
    }

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @DeleteMapping("/{userIds}")
    public ResponseResult remove(@PathVariable List<Long> userIds) {
        if(userIds.contains(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeByIds(userIds);
        return ResponseResult.okResult();
    }

    /**
     * 根据用户编号获取详细信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getUserInfoAndRoleIds(@PathVariable(value = "id") Long id){
        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(id);
        //当前用户所具有的角色id表
        List<Long> roleId = roleService.selectRoleIdByUserId(id);
        UserInfoAndRoleIdsVo userInfoAndRoleIdsVo = new UserInfoAndRoleIdsVo(user,roles,roleId);
        return ResponseResult.okResult(userInfoAndRoleIdsVo);
    }
    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody User user){
        userService.updateUser(user);
        return ResponseResult.okResult();
    }

}
