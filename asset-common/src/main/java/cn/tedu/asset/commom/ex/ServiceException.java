package cn.tedu.asset.commom.ex;


import cn.tedu.asset.commom.response.StatusCode;
import lombok.Getter;

public class ServiceException extends RuntimeException{
    @Getter
    private StatusCode statusCode;

    public ServiceException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public  ServiceException (StatusCode statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
