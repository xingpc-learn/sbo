package com.xingpc.sbo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/19 18:44
 * @Version: 1.0
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter process ......");
        chain.doFilter(request,response );
    }

    @Override
    public void destroy() {

    }

}
