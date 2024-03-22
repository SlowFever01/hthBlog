package com.hth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hth.domain.ResponseResult;
import com.hth.domain.entity.SysUserRole;
import com.hth.domain.entity.User;
import com.hth.domain.vo.PageVo;
import com.hth.domain.vo.UserInfoVo;
import com.hth.domain.vo.UserVo;
import com.hth.enums.AppHttpCodeEnum;
import com.hth.exception.SystemException;
import com.hth.mapper.UserMapper;
import com.hth.service.SysUserRoleService;
import com.hth.service.UserService;
import com.hth.utils.BeanCopyUtils;
import com.hth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 13:50:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserRoleService userRoleService;
    @Override
    public ResponseResult userInfo() {
        //获取userId
        Long userId = SecurityUtils.getUserId();
        //根据userId查询用户
        User user = getById(userId);
        //封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        //TODO:将传进来的用户id和服务端的id进行对比，如果存在该id才允许修改，否则抛出异常——“找不到该用户”，需要到AppHttpCodeEnum里面定义USER_ID_ERROR。
        if (!Objects.equals(SecurityUtils.getUserId(), user.getId())) {
            throw new SystemException(AppHttpCodeEnum.USER_ID_ERROR);
        }
        //TODO:对要更新的字段进行限制，防止传入密码等敏感字段。
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, user.getId());
        updateWrapper.set(User::getNickName, user.getNickName())
                .set(User::getAvatar, user.getAvatar())
                .set(User::getSex, user.getSex())
                .set(User::getUpdateBy, user.getId())
                .set(User::getUpdateTime, new Date());

        //如果传入的第一个参数是user，那么会更新所有的字段，使用null只会更新上面提到的字段
        baseMapper.update(null,updateWrapper);
/*        updateById(user);*/
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        save(user);
        //存入数据库
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(user.getUserName()),User::getUserName,user.getUserName());
        queryWrapper.eq(StringUtils.hasText(user.getStatus()),User::getStatus,user.getStatus());
        queryWrapper.eq(StringUtils.hasText(user.getPhonenumber()),User::getPhonenumber,user.getPhonenumber());

        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        List<User> users = page.getRecords();
        List<UserVo> userVos = users.stream()
                .map(u -> BeanCopyUtils.copyBean(u, UserVo.class))
                .collect(Collectors.toList());
        PageVo pageVo = new PageVo();
        pageVo.setRows(userVos);
        pageVo.setTotal(page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public boolean checkUserNameUnique(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper)==0;
    }

    @Override
    public boolean checkPhoneUnique(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhonenumber,user.getPhonenumber());
        return count(queryWrapper)==0;
    }

    @Override
    public boolean checkEmailUnique(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,user.getEmail());
        return count(queryWrapper)==0;
    }

    @Override
    public ResponseResult addUser(User user) {
        //密码加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        if(user.getRoleIds()!=null&&user.getRoleIds().length>0){
            insertUserRole(user);
        }
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId,user.getId());
        userRoleService.remove(queryWrapper);
        //新增用户与角色管理
        insertUserRole(user);
        //更新用户信心
        updateById(user);
    }

    private void insertUserRole(User user) {
        List<SysUserRole> sysUserRoles = Arrays.stream(user.getRoleIds())
                .map(roleId -> new SysUserRole(user.getId(), roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(sysUserRoles);
    }

    private boolean userNameExist(String userName){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper)>0; //如果count>0,说明用户名已存在，返回true
    }
    private boolean nickNameExist(String nickName){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        return count(queryWrapper)>0; //如果count>0,说明用户名已存在，返回true
    }

}
