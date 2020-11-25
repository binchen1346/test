package com.bjpowernode.Handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
    /**
     * preHandle :叫做预处理方法
     * 参数：
     * request：请求应答
     * response：请求响应
     * handler：被拦截的控制器对象
     * 返回值：boolean
     *          true：拦截器通过：执行的过程：拦截器的preHandle--->控制器的方法--->拦截器的postHandle-->拦截器的afterCompletion
     *          false：拦截器不通过：拦截器的preHandle
     * 特点：
     * 1：方法在控制器方法之前执行，用户请求先到达此方法
     * 2：在这个方法中可以获取请求的信息，验证验证请求是否符合要求
     *      可以验证用户是否登入，是否有权限登入这个地址
     *          如果验证失败，则截断请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器方法的preHandle处理");
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        String loginName="";
        Object sttr=request.getSession().getAttribute("user");
        if (sttr!=null){
          loginName=(String) sttr;
        }
        if (!loginName.equals("zs")){
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }

        return true;
    }
}
