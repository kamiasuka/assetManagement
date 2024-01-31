package cn.tedu.asset.admin.exception;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.commom.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器:处理所有的由控制器Controller抛出的异常.
 * ControllerAdvice注解：
 *   1.标识此类为全局异常处理器的类;
 *   2.一旦controller中抛出了异常,框架会先检查有没有定义全局异常处理器;
 *     2.1 如果有,则到此类中找到对应的异常处理方法,处理此异常;
 *     2.2 如果没有,则走 Spring MVC 默认的异常处理机制[不友好];
 * RestControllerAdvice注解：
 *     组合注解,相当于 @ControlerAdvice + @ResponseBody
 */
@Slf4j
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    //捕获业务层异常
    @ExceptionHandler
    public JsonResult doHandleServiceException(ServiceException ex){
        log.error(ex.getStatusCode().getMsg());
        //给客户端响应数据
        return new JsonResult(ex.getStatusCode());
    }

    /**
     * 第1个异常处理方法:处理参数异常[控制器方法中手动抛出的异常]
     * ExceptionHandler注解:标识此方法为异常处理方法;
     * @param ex 异常对象
     * @return JsonResult
     * 1.控制器抛出异常,找到全局异常处理器类;
     * 2.找到对应异常的异常处理方法进行处理;
     * 3.如果没有定义对应异常处理方法,则会找该异常的父类异常的处理方法进行处理;
     * 4.如果父类异常处理方法也没有,则会找可以处理所有异常的Throwable异常处理方法处理;
     * 5.如果没有定义Throwable异常处理方法,则会走SpringMVC默认的异常处理机制[不友好].
     */
    @ExceptionHandler
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        //ex.getMessage()方法:获取异常提示消息
        String message = ex.getMessage();
        log.error("IllegalArgumentException: " + message);

        return new JsonResult(StatusCode.OPERATION_FAILED, message);
    }

    /**
     * 第2个异常处理方法: 处理RuntimeException异常
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        String message = ex.getMessage();
        log.error("RuntimeException: " + message);

        return new JsonResult(StatusCode.OPERATION_FAILED, message);
    }

    /**
     * 第3个异常处理方法: 处理Validation校验POJO类参数异常
     */
    @ExceptionHandler
    public JsonResult doHandleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        //验证pojo类型参数时,获取提示消息:ex.getFieldError().getDefaultMessage();
        String message = ex.getFieldError().getDefaultMessage();

        return new JsonResult(StatusCode.VALIDATED_ERROR, message);
    }

    /**
     * 第4个异常处理方法：处理Validation校验非POJO类参数异常
     */
    @ExceptionHandler
    public JsonResult doHandleConstraintViolationException(ConstraintViolationException ex){
        String message = ex.getMessage().split(":")[1].trim();

        return new JsonResult(StatusCode.VALIDATED_ERROR, message);
    }

    /**
     * 能处理控制器抛出的所有异常;
     * 如果没有任何一个异常处理方法处理异常时,则会执行此方法处理异常.
     */
}







