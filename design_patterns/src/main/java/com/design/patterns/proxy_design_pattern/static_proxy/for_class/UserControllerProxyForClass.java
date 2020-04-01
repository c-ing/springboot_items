package com.design.patterns.proxy_design_pattern.static_proxy.for_class;

import com.design.patterns.proxy_design_pattern.static_proxy.UserVo;
import com.design.patterns.proxy_design_pattern.static_proxy.for_interface.MetricsCollector;
import com.design.patterns.proxy_design_pattern.static_proxy.for_interface.UserController;

/**
 * @Auther: cdc
 * @Date: 2020/3/30 11:40
 * @Description: 创建一个代理类
 *
 * 对于这种外部类的扩展，我们一般都是采用继承的方式。这里也不例外。我们让代理类继承
 * 原始类，然后扩展附加功能。
 */
public class UserControllerProxyForClass extends UserController {

    private MetricsCollector metricsCollector;

    public UserControllerProxyForClass() {
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = super.login(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = super.register(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        return userVo;
    }

    public static void main(String[] args) {
        System.out.println("基于类的静态代理模式");
        UserController userController = new UserControllerProxyForClass();
        userController.register("","");
        userController.login("", "");
    }
}
