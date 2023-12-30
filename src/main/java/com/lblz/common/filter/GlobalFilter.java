package com.lblz.common.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author lblz
 * @description
 * @date 2022/4/3 16:11
 */
@Order(1) //@Order(1)：表示过滤器的顺序
@WebFilter(filterName = "piceaFilter", urlPatterns = "/*" , initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080")})
public class GlobalFilter implements Filter {
    private String url;
    /**
     * 可以初始化Filter在web.xml里面配置的初始化参数
     * filter对象只会创建一次，init方法也只会执行一次。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.url = filterConfig.getInitParameter("URL");
        System.out.println("初始化方法:"+url);
        Filter.super.init(filterConfig);
    }

    /**
     * 主要的业务代码编写方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("方法前执行...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("方法后执行...");
    }

    /**
     * 在销毁Filter时自动调用。
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("我是过滤器的被销毁时调用的方法！，活不下去了................" );
    }
}
