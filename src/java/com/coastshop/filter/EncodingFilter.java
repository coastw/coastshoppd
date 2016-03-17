/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.filter;

import java.io.IOException;
import javax.servlet.*;
/**
 *
 * @author Coast
 */
public class EncodingFilter implements Filter{
    private String charset;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.charset = filterConfig.getInitParameter("charset");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(this.charset);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
}
