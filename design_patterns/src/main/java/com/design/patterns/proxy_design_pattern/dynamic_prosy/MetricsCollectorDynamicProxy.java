package com.design.patterns.proxy_design_pattern.dynamic_prosy;

import com.design.patterns.proxy_design_pattern.static_proxy.for_interface.IUserController;
import com.design.patterns.proxy_design_pattern.static_proxy.for_interface.MetricsCollector;
import com.design.patterns.proxy_design_pattern.static_proxy.for_interface.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: cdc
 * @Date: 2020/3/30 13:37
 * @Description:
 *  所谓动态代理（Dynamic Proxy），就是我们不
 * 事先为每个原始类编写代理类，而是在运行的时候，动态地创建原始类对应的代理类，然后
 * 在系统中用代理类替换掉原始类。
 */
public class MetricsCollectorDynamicProxy {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MetricsCollector metricsCollector;

    public MetricsCollectorDynamicProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        //为指定的接口创建代理类
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            logger.info("调用代理方法前");
            Object result = method.invoke(proxiedObject, args);
            logger.info("调用代理方法后");
            long endTimestamp = System.currentTimeMillis();
            String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
            logger.info(apiName + " 花费时间：" + (endTimestamp-startTimestamp));
            metricsCollector.recordRequest();
            return result;
        }
    }

    public static void main(String[] args) {
        MetricsCollectorDynamicProxy proxy = new MetricsCollectorDynamicProxy();
        IUserController userController = (IUserController)proxy.createProxy(new UserController());
        userController.register("", "");
    }

}
