package com.easytoolsoft.template.data.mybatis.service.impl;

import java.util.Date;

import com.easytoolsoft.mybatis.service.AbstractCrudService;
import com.easytoolsoft.template.data.mybatis.repos.EventRepository;
import com.easytoolsoft.template.data.mybatis.domain.Event;
import com.easytoolsoft.template.data.mybatis.domain.example.EventExample;
import com.easytoolsoft.template.data.mybatis.service.EventService;
import org.springframework.stereotype.Service;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Service
public class EventServiceImpl
    extends AbstractCrudService<EventRepository, Event, EventExample, Integer>
    implements EventService {

    @Override
    protected EventExample getPageExample(final String fieldName, final String keyword) {
        final EventExample example = new EventExample();
        example.createCriteria().andFieldLike(fieldName, keyword);
        return example;
    }

    @Override
    public void clear() {
        this.dao.deleteByExample(null);
    }

    @Override
    public void add(final String source, final String account, final String message, final String level,
                    final String url) {
        final Event event = Event.builder()
            .source(source)
            .account(account)
            .userId(-1)
            .message(message)
            .level(level)
            .url(url)
            .gmtCreated(new Date())
            .build();
        this.add(event);
    }
}