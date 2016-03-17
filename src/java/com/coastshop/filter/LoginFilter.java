/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Coast
 */
public class LoginFilter implements Filter {

    private String uncheck;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //从初始化参数中取得放行：url-pattern(uncheck)
        this.uncheck = filterConfig.getInitParameter("uncheck");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //uncheck pattern

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /*
         * 如果页面是login页面则不检查直接放行
         * 否则检查session，用户是否登录
         *  out.println("<h1>getContextPath : " + request.getContextPath() + "</h1>"); //    /CoastShop
         *  out.println("<h1>getRequestURL : " + request.getRequestURL() + "</h1>"); //      http://localhost:8084/CoastShop/TestLogin
         *  out.println("<h1>getRequestURI : " + request.getRequestURI()+ "</h1>");//        /CoastShop/TestLogin
         *  out.println("<h1>getServletPath : " + request.getServletPath()+ "</h1>");//        /CoastShop/TestLogin
         */
//        System.out.println(req.getServletPath());
        //System.out.println("-----request : " + req.getServletPath());
        
        if ( isPass(req.getRequestURI()) ) {   //uncheck
            //System.out.println("----IsPassed");
            chain.doFilter(request, response);
        } else {  //check
            HttpSession ses = req.getSession();
            if (ses.getAttribute("user") == null) {
                //System.out.println("----NotPassed----NotLogin");
                res.sendRedirect("login.jsp");
            } else {
                //System.out.println("----NotPassed----IsLogin");
                chain.doFilter(request, response);
            }
        }
    }
    
    /**
     * 检查请求地址是否符合放行模式(登录或注册页面,js,css,jpeg,png等都不进行检查)
     * @param requestURI
     * @return 如果请求地址符合放行要求则返回true,否则返回false.
     */
    private boolean isPass(String requestURI){
        boolean flag = false;
        String[] patterns = this.uncheck.split(",");
        for (int i = 0; i < patterns.length; i++) {
            flag |= requestURI.endsWith(patterns[i]);
        }
        return flag;
    }

    @Override
    public void destroy() {
    }
}
