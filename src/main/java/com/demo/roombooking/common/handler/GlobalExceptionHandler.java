package com.demo.roombooking.common.handler;



import com.demo.roombooking.common.exception.BusinessException;
import com.demo.roombooking.common.resp.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NonUniqueResultException;

/**
 * 使用{@link ControllerAdvice} 与 {@link ExceptionHandler} 实现全局的 Controller 层的异常处理
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     *
     * @param e 异常对象
     * @return {@link JsonResponse}
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    JsonResponse handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);


        return new JsonResponse(JsonResponse.FAILURE,"未知异常:"+e.getMessage());
    }

    /**
     * 处理所有业务异常
     *
     * @param e 异常对象
     * @return {@link JsonResponse}
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    JsonResponse handleBusinessException(BusinessException e) {
        LOGGER.error(e.getMessage(), e);
        return new JsonResponse(JsonResponse.FAILURE,e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e 异常对象
     * @return {@link JsonResponse}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder errorMessage = new StringBuilder("校验失败：");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("; ");
        }
        return new JsonResponse(JsonResponse.FAILURE, errorMessage.toString());
    }

    /**
     * 处理NonUniqueResultException异常
     *
     * @param e 异常对象
     * @return {@link JsonResponse}
     */
    @ExceptionHandler(NonUniqueResultException.class)
    @ResponseBody
    JsonResponse handleNonUniqueResultException(MethodArgumentNotValidException e) {

        LOGGER.error(e.getMessage(), e);

        return new JsonResponse(JsonResponse.FAILURE, "数据库查询结果不唯一");
    }
}


