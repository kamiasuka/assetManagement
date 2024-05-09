package cn.tedu.asset.admin.Interceptor;

import cn.tedu.asset.commom.jwt.JwtUtils;
import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.commom.response.StatusCode;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);
        //判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作, 放行...");
            return true;
        }
        /**
         * OPTIONS的问题
         * 在controller中去获取我的请求头中的信息，是可以获取到的，但是在JWT验证的拦截器中无法获取得到。
         * 原因：
         * 实际上发送了两次请求，第一次为 OPTIONS 请求，第二次才 GET/POST… 请求在OPTIONS请求中，不会携带请求头的参数，所以在拦截器上获取请求头为空。
         * 自定义的拦截器拦截成功第一次请求不能通过，就不能获取第二次的请求了 GET/POST…第一次请求不带参数，第二次请求才带参数。
         */
        //如果请求为 OPTIONS 请求，则返回 true,否则需要通过jwt验证
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return true;
        }

        //获取请求头中的令牌（token）
        String jwt = request.getHeader("Authorization");
        System.out.println("获取请求头中的令牌"+jwt);
        //判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录的信息");
            String message = "操作失败，您的登录信息已经过期，请重新登录！";
            JsonResult jsonResult = JsonResult.fail(StatusCode.NOT_LOGIN,message);
            String result = JSON.toJSONString(jsonResult);
            response.getWriter().write(result);
            return false;
        }

        //解析token，如果解析失败，返回错误结果
        try {
            System.out.println("解析前端发送的token令牌:"+jwt);
            JwtUtils.parseJWT(jwt);
        } catch (JwtException e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            String message = "解析令牌失败, 返回未登录错误信息!";
            JsonResult jsonResult = JsonResult.fail(StatusCode.LOGIN_OUT_TIME,message);
            String result = JSON.toJSONString(jsonResult);
            response.getWriter().write(result);
            return false;
        }

        //放行
        log.info("令牌合法, 放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
