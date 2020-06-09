package com.mmall.concurrency;

import com.mmall.concurrency.example.threadLocal.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: cdc
 * @Date: 2020/6/9 16:48
 * @Description:
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle==============");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}
