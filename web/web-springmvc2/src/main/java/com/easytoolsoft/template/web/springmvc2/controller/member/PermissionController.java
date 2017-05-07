package com.easytoolsoft.template.web.springmvc2.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.template.common.annotation.OpLog;
import com.easytoolsoft.template.common.model.ResponseResult;
import com.easytoolsoft.template.data.mybatis.domain.Permission;
import com.easytoolsoft.template.data.mybatis.domain.example.PermissionExample;
import com.easytoolsoft.template.data.mybatis.service.PermissionService;
import com.easytoolsoft.template.web.springmvc2.controller.common.BaseController;
import com.easytoolsoft.template.web.springmvc2.model.DataGridPager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@RestController
@RequestMapping(value = "/rest/member/permission")
public class PermissionController
    extends BaseController<PermissionService, Permission, PermissionExample, Integer> {

    @GetMapping(value = "/list")
    @OpLog(name = "获取权限列表")
    @RequiresPermissions("membership.permission:view")
    public Map<String, Object> list(final DataGridPager pager, final Integer id) {
        final int moduleId = (id == null ? 0 : id);
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Permission> list = this.service.getByPage(pageInfo, moduleId);
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", list.size());
        modelMap.put("rows", list);
        return modelMap;
    }

    @PostMapping(value = "/add")
    @OpLog(name = "增加权限")
    @RequiresPermissions("membership.permission:add")
    public ResponseResult add(final Permission po) {
        final ResponseResult<String> result = new ResponseResult<>();
        po.setGmtCreated(new Date());
        po.setGmtModified(new Date());
        this.service.add(po);
        this.service.reloadCache();
        return result;
    }

    @PostMapping(value = "/edit")
    @OpLog(name = "修改权限")
    @RequiresPermissions("membership.permission:edit")
    public ResponseResult edit(final Permission po) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.editById(po);
        this.service.reloadCache();
        return result;
    }

    @PostMapping(value = "/remove")
    @OpLog(name = "删除权限")
    @RequiresPermissions("membership.permission:remove")
    public ResponseResult remove(final Integer id) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.removeById(id);
        this.service.reloadCache();
        return result;
    }
}
