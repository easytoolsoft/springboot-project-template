package com.easytoolsoft.template.common.spring.aop;

import com.easytoolsoft.template.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理器
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return new ResponseResult().failure(400, "参数解析失败", e.toString());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return new ResponseResult().failure(405, "不支持当前请求方法", e.toString());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("不支持当前媒体类型", e);
        return new ResponseResult().failure(415, "不支持当前媒体类型", e.toString());
    }

    ///**
    // * 401 - Unauthorized
    // */
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    //@ExceptionHandler(UnauthorizedException.class)
    //public ResponseResult handleUnauthorizedException(UnauthorizedException e) {
    //    log.error("没有权限", e);
    //    return new ResponseResult().failure(401, "对不起!您没有权限,访问拒绝.", e.toString());
    //}

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error("服务运行异常", e);
        return new ResponseResult().failure(500, "服务运行异常", e.toString());
    }
}