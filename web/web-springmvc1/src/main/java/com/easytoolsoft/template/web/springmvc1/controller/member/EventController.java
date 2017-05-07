package com.easytoolsoft.template.web.springmvc1.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.template.common.annotation.OpLog;
import com.easytoolsoft.template.common.model.ResponseResult;
import com.easytoolsoft.template.data.mybatis.domain.Event;
import com.easytoolsoft.template.data.mybatis.domain.example.EventExample;
import com.easytoolsoft.template.data.mybatis.service.EventService;
import com.easytoolsoft.template.web.springmvc1.controller.common.BaseController;
import com.easytoolsoft.template.web.springmvc1.model.DataGridPager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ApiOperation(value = "分页获取系统日志列表", notes = "分页获取系统日志列表")
    @OpLog(name = "分页获取系统日志列表")
    @GetMapping(value = {""})
    public Map<String, Object> list(final DataGridPager pager, final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Event> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }

    @ApiOperation(value = "获取事件详细信息", notes = "根据url的id来获取事件详细信息")
    @ApiImplicitParam(name = "id", value = "事件ID", required = true, dataType = "Integer")
    @OpLog(name = "获取事件详细信息")
    @GetMapping(value = "/{id}")
    public Event getById(@PathVariable Integer id) {
        throw new RuntimeException("");
        //return this.service.getById(id);
    }

    @ApiOperation(value = "删除事件", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "事件ID", required = true, dataType = "Integer")
    @OpLog(name = "删除事件")
    @DeleteMapping(value = "/{id}")
    public int remove(@PathVariable Integer id) {
        return this.service.removeById(id);
    }

    @ApiOperation(value = "清除系统日志", notes = "清除所有系统日志")
    @OpLog(name = "清除系统日志")
    @DeleteMapping(value = "")
    public ResponseResult clear() {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.clear();
        return result;
    }
}