package com.easytoolsoft.template.web.springmvc2.service;

import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.template.web.springmvc2.domain.Event;
import com.easytoolsoft.template.web.springmvc2.domain.example.EventExample;

/**
 * 系统事件或日志服务类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface EventService extends CrudService<Event, EventExample, Integer> {
    /**
     *
     */
    void clear();

    /**
     * @param source
     * @param account
     * @param message
     * @param level
     * @param url
     */
    void add(String source, String account, String message, String level, String url);
}