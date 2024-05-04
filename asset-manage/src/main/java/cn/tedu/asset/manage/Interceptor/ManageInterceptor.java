package cn.tedu.asset.manage.Interceptor;

import cn.tedu.asset.commom.jwt.JwtUtils;
import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.commom.response.StatusCode;
import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class ManageInterceptor implements HandlerInterceptor {
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
            System.out.println("解析token令牌:"+jwt);
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
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
