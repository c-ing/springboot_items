package com.mmall.concurrency;

import com.mmall.concurrency.example.threadLocal.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: cdc
 * @Date: 2020/6/9 16:35
 * @Description:
 */

public class HttpFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter,{},{}",Thread.currentThread().getId(),request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
