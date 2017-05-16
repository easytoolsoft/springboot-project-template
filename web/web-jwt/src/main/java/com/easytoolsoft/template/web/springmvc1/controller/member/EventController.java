package com.easytoolsoft.template.web.springmvc1.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.commons.support.annotation.OpLog;
import com.easytoolsoft.commons.support.model.ResponseResult;
import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.template.data.mybatis.domain.Event;
import com.easytoolsoft.template.data.mybatis.domain.example.EventExample;
import com.easytoolsoft.template.data.mybatis.service.EventService;
import com.easytoolsoft.template.web.springmvc1.controller.common.BaseController;
import com.easytoolsoft.template.web.springmvc1.model.DataGridPager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@RestController
@RequestMapping(value = "/rest/member/event")
public class EventController
    extends BaseController<EventService, Event, EventExample, Integer> {
    @GetMapping(value = "/list")
    @OpLog(name = "分页获取系统日志列表")
    //@RequiresPermissions("membership.event:view")
    public Map<String, Object> list(final DataGridPager pager, final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Event> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }

    @PostMapping(value = "/remove")
    @OpLog(name = "删除系统日志")
    //@RequiresPermissions("membership.event:remove")
    public ResponseResult remove(final Integer id) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.removeById(id);
        return result;
    }

    @GetMapping(value = "/clear")
    @OpLog(name = "清除系统日志")
    //@RequiresPermissions("membership.event:clear")
    public ResponseResult clear() {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.clear();
        return result;
    }
}