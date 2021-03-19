package com.spc.ad.advice;

import com.spc.ad.exception.AdException;
import com.spc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tangQ.
 * GlobalExceptionAdvice For 全局异常拦截
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException e) {
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(e.getMessage());
        return response;
    }
}
