package com.easytoolsoft.template.web.springmvc2.aop;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.easytoolsoft.template.web.springmvc2.annotation.OpLog;
import com.easytoolsoft.template.web.springmvc2.domain.Event;
import com.easytoolsoft.template.web.springmvc2.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
public class OpLogAspect {
    @Resource
    private EventService eventService;

    @Pointcut("@annotation(com.easytoolsoft.template.web.springmvc2.annotation.OpLog)")
    public void pointcut() {
    }

    @After("pointcut()")
    public void doAfter(final JoinPoint joinPoint) {
        try {
            this.logEvent(joinPoint, "INFO", "");
        } catch (final Exception e) {
            log.error("异常信息:{}", e);
        }
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfterThrowing(final JoinPoint joinPoint, final Throwable e) {
        try {
            this.logEvent(joinPoint, "ERROR", this.getExceptionStack(e));
        } catch (final Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
    }

    private void logEvent(final JoinPoint joinPoint, final String level, String message) {
        try {
            final HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest();
            final Map<String, String> methodInfo = this.getMethodInfo(joinPoint);
            final String source = MapUtils.getString(methodInfo, "source", "");
            message = String.format("name:%s;params:%s;desc:%s;detail:%s",
                MapUtils.getString(methodInfo, "name", ""),
                MapUtils.getString(methodInfo, "params", ""),
                MapUtils.getString(methodInfo, "desc", ""), message);
            final Event event = Event.builder()
                .source(source)
                .account("")
                .userId(0)
                .message(message)
                .level(level)
                .url(req.getRequestURL().toString())
                .gmtCreated(new Date()).build();
            this.eventService.add(event);
        } catch (final Exception e) {
            log.error("记录系统事件出错", e);
        }
    }

    private Map<String, String> getMethodInfo(final JoinPoint joinPoint) throws Exception {
        final Map<String, String> methodInfoMap = new HashMap<>(3);
        final String targetName = joinPoint.getTarget().getClass().getName();
        final String methodName = joinPoint.getSignature().getName();
        final Object[] arguments = joinPoint.getArgs();
        final Class targetClass = Class.forName(targetName);
        final Method[] methods = targetClass.getMethods();
        methodInfoMap.put("source", targetName + ":" + methodName);

        for (final Method method : methods) {
            if (method.getName().equals(methodName)) {
                final Class[] methodParameterTypes = method.getParameterTypes();
                if (methodParameterTypes.length == arguments.length) {
                    methodInfoMap.put("name", method.getAnnotation(OpLog.class).name());
                    methodInfoMap.put("desc", method.getAnnotation(OpLog.class).desc());
                    methodInfoMap.put("params", StringUtils.join(arguments, ","));
                    break;
                }
            }
        }
        return methodInfoMap;
    }

    private String getExceptionStack(final Throwable ex) {
        String stackInfo = "";
        try (StringWriter out = new StringWriter()) {
            final PrintWriter printWriter = new PrintWriter(out);
            ex.printStackTrace(printWriter);
            stackInfo = out.toString();
            printWriter.close();
        } catch (final Exception e) {
            log.error("获取异常堆栈信息出错", e);
        }
        return stackInfo;
    }
}

