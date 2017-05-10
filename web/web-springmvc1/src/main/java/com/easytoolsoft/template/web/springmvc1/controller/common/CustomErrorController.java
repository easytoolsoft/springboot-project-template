package com.easytoolsoft.template.web.springmvc1.controller.common;

import com.easytoolsoft.commons.support.web.ErrorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Slf4j
@Controller
@RequestMapping("/customError")
public class CustomErrorController extends ErrorController {
    public CustomErrorController(final ErrorAttributes errorAttributes,
                                 final ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }
}

