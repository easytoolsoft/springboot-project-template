package com.easytoolsoft.template.web.springmvc1.spring.aop;

import com.easytoolsoft.commons.support.aop.ExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/

@Slf4j
@ResponseBody
@ControllerAdvice
public class CustomExceptionAdvice extends ExceptionAdvice {

}