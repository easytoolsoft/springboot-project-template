package com.easytoolsoft.template.common.spring.resolver;

import com.easytoolsoft.template.common.model.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zhiwei.deng
 * @date 2017-04-26
 **/
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final String basePackage;
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate, String basePackage) {
        this.delegate = delegate;
        this.basePackage = basePackage;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        String declaringClazzName = returnType.getMethod().getDeclaringClass().getName();
        if (StringUtils.startsWith(declaringClazzName, this.basePackage)) {
            if (returnValue instanceof ResponseResult) {
                delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            } else {
                ResponseResult<Object> result = new ResponseResult<>(returnValue);
                delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
            }
            return;
        }
        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}