package com.springboot.redis.demo.stock;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 11:26
 * @Description: 初始化队列的使用
 * 在 Servlet API 中有一个 ServletContextListener 接口，
 *  * 它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。
 *  * 当Servlet 容器启动或终止Web 应用时，会触发ServletContextEvent 事件，该事件由ServletContextListener 来处理。
 *  * 在 ServletContextListener 接口中定义了处理ServletContextEvent 事件的两个方法contextInitialized、contextDestroyed
 * ————————————————
 * 版权声明：本文为CSDN博主「luu_一只程序猿」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/weixin_40663800/article/details/90703154
 * github: https://github.com/zq99299/cache-pdp/find/040-044
 * 博客： https://zq99299.github.io/note-book/cache-pdp/040.html#%E4%B8%A4%E7%A7%8D%E8%AF%B7%E6%B1%82-controller-%E5%B0%81%E8%A3%85
 *
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 工作线程池，内存队列初始化
        RequestProcessorThreadPool.init();
    }

    /**
     * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
