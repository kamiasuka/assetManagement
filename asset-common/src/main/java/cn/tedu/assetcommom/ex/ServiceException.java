package cn.tedu.assetcommom.ex;


import cn.tedu.assetcommom.response.StatusCode;
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
