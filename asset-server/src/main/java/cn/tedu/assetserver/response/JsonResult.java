package cn.tedu.assetserver.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 统一响应结果返回的类
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    //自定义状态码,提示消息,具体数据
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 构造方法1：无参构造,NoArgsConstructor注解;
     */

    /**
     * 构造方法2：全参构造,AllArgsConstructor注解;
     * 适用场景：适用于所有需要数据data返回的控制器方法;
     */

    /**
     * 构造方法3：只有code和msg;
     * 适用场景：适用于所有没有具体数据data返回的控制器方法;
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 创建2个构造方法,使用自定义枚举状态码,
     * 第1个：适用于没有具体数据返回的控制器方法;
     * 第2个：适用于有具体数据返回的控制器方法
     */
    //使用枚举类的第1个构造方法：无数据返回
    public JsonResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    //使用枚举类的第2个构造方法：有数据返回
    public JsonResult(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    /**
     * 创建2个静态方法：用于快速构建JsonResult对象;
     *               只针对于操作成功的场景;
     * 静态方法1：针对于有具体数据返回的操作成功的场景;
     * 静态方法2：针对于没有具体数据返回的操作成功的场景;
     */
    public static JsonResult ok(Object data){
        return new JsonResult(StatusCode.OPERATION_SUCCESS, data);
    }
    public static JsonResult ok(){
        return ok(null);
    }
}








