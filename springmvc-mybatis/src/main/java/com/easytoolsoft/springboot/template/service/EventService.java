package com.easytoolsoft.springboot.template.service;

import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.springboot.template.domain.Event;
import com.easytoolsoft.springboot.template.domain.example.EventExample;

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