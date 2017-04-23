package com.easytoolsoft.springboot.template.web.common;

import java.io.IOException;
import java.lang.reflect.Type;

import com.easytoolsoft.springboot.template.web.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * ResponseResult<T> 序列化输出转换类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Slf4j
public class ResponseResult2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private final String BASE_PATH = "/rest/";

    public ResponseResult2HttpMessageConverter() {
        log.debug("load {}", this.getClass().getName());
    }

    @Override
    protected void writeInternal(final Object object, final Type type, final HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        if (object instanceof ResponseResult) {
            super.writeInternal(object, type, outputMessage);
        } else {
            final ResponseResult<Object> responseResult = new ResponseResult<>(object);
            super.writeInternal(responseResult, type, outputMessage);
        }
    }

    @Override
    protected void writeInternal(final Object object, final HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        this.writeInternal(object, null, outputMessage);
    }
}
