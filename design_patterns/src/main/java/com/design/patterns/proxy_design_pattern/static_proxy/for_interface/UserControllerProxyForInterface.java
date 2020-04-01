package com.design.patterns.proxy_design_pattern.static_proxy.for_interface;

import com.design.patterns.proxy_design_pattern.static_proxy.UserVo;

/**
 * @Auther: cdc
 * @Date: 2020/3/30 11:29
 * @Description: 参照基于接口而非实现编程的设计思想，
 * 将原始类对象替换为代理类对象的时候，为了让代码改动尽量少，
 * 在刚刚的代理模式的代码实现中，代理类和原始类需要实现相同的接口。
 */
public class UserControllerProxyForInterface implements IUserController {

    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxyForInterface(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        // 委托
        UserVo userVo = userController.login(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        metricsCollector.recordRequest();

        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        // 委托
        UserVo userVo = userController.register(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        metricsCollector.recordRequest();

        return null;
    }

    public static void main(String[] args) {
        System.out.println("基于接口的静态代理模式");
        //UserControllerProxy使用举例
        //因为原始类和代理类实现相同的接口，是基于接口而非实现编程
        //将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码
        IUserController userController = new UserControllerProxyForInterface(new UserController());
        userController.register("", "");
        userController.login("", "");
    }
}
