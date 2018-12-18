package com.example.jsspringzuul.uitl;/*
package com.cloud.dmspringzuulserver.uitl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ResponseUtil {
    public static ResponseEntity createResponseEntity(String errorMsg, ErrorCode errorCode, HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        ErrorResponseMap errorResponseMap = createErrorResponse(status, errorMsg, errorCode, request, ex);
        return new ResponseEntity<>(errorResponseMap, status);
    }

    public static ErrorResponseMap createErrorResponse(HttpStatus status, String errorMsg, ErrorCode errorCode, HttpServletRequest request, Throwable ex) {
        if (status == null) {
            status = getStatus(request);
        }
        ErrorResponseMap errorResponseMap = new ErrorResponseMap();
        */
/*Error error = new Error(status.value(), errorMsg, ex == null ? "" : ex.getMessage(),
                errorCode == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : errorCode.value(), ex == null ? "" : ex.getClass().getCanonicalName());*//*

        errorResponseMap.setSuccess(false);
        errorResponseMap.setMessage(ex.getMessage());
        errorResponseMap.setError(error);
//        String stackTrace = StringUtils.arrayToDelimitedString(ex.getStackTrace(), "hahaha");
//        errorResponseMap.setStackTrace(stackTrace);
        return errorResponseMap;
    }

    public static HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
*/
