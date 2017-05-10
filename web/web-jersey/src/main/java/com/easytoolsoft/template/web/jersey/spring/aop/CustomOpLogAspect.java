package com.easytoolsoft.template.web.jersey.spring.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.easytoolsoft.commons.support.aop.OpLogAspect;
import com.easytoolsoft.commons.support.consts.UserAuthConsts;
import com.easytoolsoft.template.data.mybatis.domain.Event;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
@Slf4j
@Aspect
@Component
public class CustomOpLogAspect extends OpLogAspect {
    @Resource
    private EventService eventService;

    @Override
    protected void logEvent(final EventParameter eventParameter) {
        final HttpServletRequest req =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        final User user = (User)req.getAttribute(UserAuthConsts.CURRENT_USER);
        if (user != null) {
            final Event event = Event.builder()
                .source(eventParameter.getSource())
                .account(user.getAccount())
                .userId(user.getId())
                .message(eventParameter.toString())
                .level(eventParameter.getLevel())
                .url(req.getRequestURL().toString())
                .gmtCreated(new Date())
                .build();
            this.eventService.add(event);
        }
    }
}

