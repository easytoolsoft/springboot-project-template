package com.easytoolsoft.template.web.springmvc1.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.template.common.annotation.CurrentUser;
import com.easytoolsoft.template.common.annotation.OpLog;
import com.easytoolsoft.template.common.security.PasswordService;
import com.easytoolsoft.template.common.model.ResponseResult;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.UserExample;
import com.easytoolsoft.template.data.mybatis.service.UserService;
import com.easytoolsoft.template.web.springmvc1.controller.common.BaseController;
import com.easytoolsoft.template.web.springmvc1.model.DataGridPager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Validated
@RestController
@RequestMapping(value = "/rest/member/user")
public class UserController
    extends BaseController<UserService, User, UserExample, Integer> {

    @Resource
    private PasswordService passwordService;

    @GetMapping(value = "/list")
    @OpLog(name = "分页获取用户列表")
    @RequiresPermissions("membership.user:view")
    public Map<String, Object> list(@CurrentUser final User loginUser, final DataGridPager pager,
                                    final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<User> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }

    @RequestMapping(value = "/add")
    @OpLog(name = "新增用户")
    @RequiresPermissions("membership.user:add")
    public ResponseResult add(@Valid final User po) {
        final ResponseResult<String> result = new ResponseResult<>();
        po.setGmtCreated(new Date());
        po.setGmtModified(new Date());
        this.service.encryptPassword(po);
        this.service.add(po);
        return result;
    }

    @RequestMapping(value = "/edit")
    @OpLog(name = "修改用户")
    @RequiresPermissions("membership.user:edit")
    public ResponseResult edit(@Valid final User po) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.encryptPassword(po);
        this.service.editById(po);
        return result;
    }

    @RequestMapping(value = "/remove")
    @OpLog(name = "删除用户")
    @RequiresPermissions("membership.user:remove")
    public ResponseResult remove(final Integer id) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.removeById(id);
        return result;
    }

    @RequestMapping(value = "/editPassword")
    @OpLog(name = "修改用户登录密码")
    @RequiresPermissions("membership.user:editPassword")
    public ResponseResult editPassword(final Integer userId, @Length(min = 6) final String password) {
        final ResponseResult<String> result = new ResponseResult<>();
        final User user = this.service.getById(userId);
        user.setPassword(password);
        this.service.encryptPassword(user);
        this.service.editById(user);
        return result;
    }

    @RequestMapping(value = "/changeMyPassword")
    @OpLog(name = "修改个人登录密码")
    @RequiresPermissions("membership.user:changeMyPassword")
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
