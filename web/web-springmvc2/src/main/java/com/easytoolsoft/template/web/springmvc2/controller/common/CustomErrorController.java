package com.easytoolsoft.template.web.springmvc2.controller.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easytoolsoft.commons.support.web.ErrorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/401", produces = "text/html")
    public ModelAndView error401Html(final HttpServletRequest request,
                                     final HttpServletResponse response) {
        final Map<String, Object> model = this.getViewModel(request, response);
        return new ModelAndView("error/404", model);
    }

    @ResponseBody
    @RequestMapping(value = "/401")
    public ResponseEntity<Map<String, Object>> error401(final HttpServletRequest request) {
        return getResponseEntity(request);
    }

}

