package com.iolll.nicesome.authorization.interceptor;

import com.google.gson.Gson;
import com.iolll.nicesome.authorization.annotation.Authorization;
import com.iolll.nicesome.authorization.manager.TokenManager;
import com.iolll.nicesome.authorization.model.TokenModel;
import com.iolll.nicesome.config.Constants;
import com.iolll.nicesome.model.base.RBuilder;
import com.iolll.nicesome.model.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author ScienJus
 * @date 2015/7/30.
 * @see com.iolll.nicesome.authorization.annotation.Authorization
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
//        //从header中得到token
//        String com.iolll.nicesome.authorization = request.getHeader(Constants.AUTHORIZATION);
//        System.out.println(com.iolll.nicesome.authorization);
//        //验证token
//        TokenModel model = manager.getToken(com.iolll.nicesome.authorization);
//        if (manager.checkToken(model)) {
//            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
//            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
//            return true;
//        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {

            //从header中得到token
            System.out.println(request.getHeaderNames());
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            System.out.println(authorization);
            //验证token
            TokenModel model = manager.getToken(authorization);
            if (manager.checkToken(model)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                return true;
            } else {

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
                PrintWriter writer = response.getWriter();
//                Map<String, String> map = new HashMap<>();
//                map.put("code", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
//                map.put("msg", "签名错误");
                Result result = RBuilder.INSTANCE.failed("签名错误");
                result.setCode(HttpServletResponse.SC_UNAUTHORIZED);
                result.setData(null);
                String s = new Gson().toJson(result);
                System.out.println(s);
                writer.write(s);

                return false;
            }
        }
        return true;
    }
}
