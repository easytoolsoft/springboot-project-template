package com.easytoolsoft.springboot.template.web.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.springboot.template.web.model.DataGridPager;
import com.easytoolsoft.springboot.template.web.model.ResponseResult;
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

    public Map<String, Object> list(final DataGridPager pager) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Model> list = this.service.getByPage(pageInfo);
        return this.getListMap(pageInfo, list);
    }

    public Map<String, Object> find(final DataGridPager pager, final String fieldName, final String keyword) {
        final PageInfo pageInfo = pager.toPageInfo();
        final List<Model> list = this.service.getByPage(pageInfo, fieldName, keyword);
        return this.getListMap(pageInfo, list);
    }

    public ResponseResult add(final Model po) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.add(po);
        return result;
    }

    public ResponseResult edit(final Model po) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.editById(po);
        return result;
    }

    public ResponseResult remove(final Type id) {
        final ResponseResult<String> result = new ResponseResult<>();
        this.service.removeById(id);
        return result;
    }

    private Map<String, Object> getListMap(final PageInfo pageInfo, final List<Model> list) {
        final Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
        return modelMap;
    }
}
