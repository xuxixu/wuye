package com.zhumeijia.wuye.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhumeijia.wuye.bean.Admin;
import com.zhumeijia.wuye.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 *
 * @Package: com.*.*.interceptor
 * @ClassName: AdminInterceptor
 * @Description:拦截器
 * @author: zk
 * @date: 2019年9月19日 下午2:20:57
 */
public class AdminInterceptor implements  HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            User user=(User)request.getSession().getAttribute("user");
            Admin admin=(Admin)request.getSession().getAttribute("admin");
            if(user !=null || admin != null){
                return true;
            }
            if(request.getRequestURI().contains("user"))
            {
                response.sendRedirect("/user/login");
            }else
            {
                response.sendRedirect("/login");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}