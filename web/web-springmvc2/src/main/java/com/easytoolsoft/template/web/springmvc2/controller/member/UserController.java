package com.easytoolsoft.template.web.springmvc2.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.template.common.annotation.CurrentUser;
import com.easytoolsoft.template.common.annotation.OpLog;
import com.easytoolsoft.template.common.auth.PasswordService;
import com.easytoolsoft.template.common.model.ResponseResult;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.UserExample;
import com.easytoolsoft.template.data.mybatis.service.UserService;
import com.easytoolsoft.template.web.springmvc2.controller.common.BaseController;
import com.easytoolsoft.template.web.springmvc2.model.DataGridPager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@RestController
@RequestMapping(value = "/rest/member/user")
public class UserController
    extends BaseController<UserService, User, UserExample, Integer> {

    @Resource(name = "ShiroPasswordService")
    private PasswordService passwordService;

    @ApiOperation(value = "分页获取系统日志列表", notes = "分页获取系统日志列表")
    @OpLog(name = "分页获取用户列表")
    @RequiresPermissions("membership.user:view")
    @GetMapping(value = "")
    public Map<String, Object> list(@CurrentUser final User loginUser, final DataGridPager pager,
                                    final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<User> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @OpLog(name = "新增用户")
    @RequiresPermissions("membership.user:add")
    @PostMapping(value = "")
    public User add(final User po) {
        po.setGmtCreated(new Date());
        po.setGmtModified(new Date());
        this.service.encryptPassword(po);
        this.service.add(po);
        return po;
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @OpLog(name = "修改用户")
    @RequiresPermissions("membership.user:edit")
    @PutMapping(value = "")
    public int edit(final User po) {
        this.service.encryptPassword(po);
        return this.service.editById(po);
    }

    @ApiOperation(value = "删除事件", notes = "根据url的id来指定删除对象")
    @OpLog(name = "删除用户")
    @RequiresPermissions("membership.user:remove")
    @DeleteMapping(value = "/{id}")
    public int remove(final Integer id) {
        return this.service.removeById(id);
    }

    @ApiOperation(value = "修改用户登录密码", notes = "修改用户登录密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String")
    })
    @OpLog(name = "修改用户登录密码")
    @RequiresPermissions("membership.user:editPassword")
    @PutMapping(value = "/{userId}/{password}")
    public int editPassword(final Integer userId, final String password) {
        final User user = this.service.getById(userId);
        user.setPassword(password);
        this.service.encryptPassword(user);
        return this.service.editById(user);
    }

    @ApiOperation(value = "修改个人登录密码", notes = "修改个人登录密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String"),
        @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String")
    })
    @OpLog(name = "修改个人登录密码")
    @RequiresPermissions("membership.user:changeMyPassword")
    @PutMapping(value = "/{password}/{oldPassword}")
    public ResponseResult changeMyPassword(@CurrentUser final User loginUser, final String password,
                                           final String oldPassword) {
        final ResponseResult<String> result = new ResponseResult<>();
        final String encryptPassword = this.passwordService.encryptPassword(oldPassword,
            loginUser.getCredentialsSalt());
        if (!encryptPassword.equals(loginUser.getPassword())) {
            result.setSuccess(false);
            result.setMsg("原账户密码错误！");
            return result;
        }
        loginUser.setPassword(password);
        this.service.encryptPassword(loginUser);
        this.service.editById(loginUser);
        return result;
    }
}