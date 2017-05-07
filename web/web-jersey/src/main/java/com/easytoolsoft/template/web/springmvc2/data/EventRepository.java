package com.easytoolsoft.template.web.springmvc2.data;

import com.easytoolsoft.mybatis.data.CrudRepository;
import com.easytoolsoft.template.web.springmvc2.domain.Event;
import com.easytoolsoft.template.web.springmvc2.domain.example.EventExample;
import org.springframework.stereotype.Repository;

/**
 * 系统事件或日志(springboot.template_event表)数据访问类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Repository
public interface EventRepository extends CrudRepository<Event, EventExample, Integer> {
}
