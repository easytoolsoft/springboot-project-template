package com.easytoolsoft.springboot.template.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.springboot.template.annotation.OpLog;
import com.easytoolsoft.springboot.template.domain.Event;
import com.easytoolsoft.springboot.template.domain.example.EventExample;
import com.easytoolsoft.springboot.template.service.EventService;
import com.easytoolsoft.springboot.template.web.controller.common.BaseController;
import com.easytoolsoft.springboot.template.web.model.DataGridPager;
import com.easytoolsoft.springboot.template.web.model.ResponseResult;
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
    public Map<String, Object> list(final DataGridPager pager, final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Event> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }

    @Override
    @PostMapping(value = "/remove")
    @OpLog(name = "删除系统日志")
    public ResponseResult remove(final Integer id) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.removeById(id);
        return result;
    }

    @GetMapping(value = "/clear")
    @OpLog(name = "清除系统日志")
    public ResponseResult clear() {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.clear();
        return result;
    }
}