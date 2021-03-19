package com.spc.ad.advice;

import com.spc.ad.annotation.IgnoreResponseAdvice;
import com.spc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by tangQ.
 *  对响应结果的增强
 *
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {

    /**
     * 判断是否需要对响应进行处理,此时需要自定义一个注解ignoreResponseAdvice
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        // 如果类上标识了@ignoreRespnseAdvice注解，则不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        // 如果方法上标识了@ignoreRespnseAdvice注解，则不拦截
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        // 对响应进行处理，执行beforeBodyWrite方法
        return true;
    }

    /**
     * 在响应视图之前执行，目的是拦截CommonResponse
     *
     * @param o                  原始的Controller需要返回的数据
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> commonResponse = new CommonResponse();

        if (null == o) {
            return commonResponse;
        } else if (o instanceof CommonResponse) {
            commonResponse = (CommonResponse<Object>) o;
        } else {
            commonResponse.setData(o);
        }

        return commonResponse;
    }
}
