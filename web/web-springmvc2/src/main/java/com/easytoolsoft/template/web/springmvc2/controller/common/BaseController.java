package com.easytoolsoft.template.web.springmvc2.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.mybatis.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共Action控制器类
 *
 * @param <Service>
 * @param <Model>
 * @param <Example>
 * @param <Type>
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Slf4j
public class BaseController<Service extends CrudService<Model, Example, Type>, Model, Example, Type> {
    @Autowired
    protected Service service;

    protected Map<String, Object> getListMap(final PageInfo pageInfo, final List<Model> list) {
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }
}
