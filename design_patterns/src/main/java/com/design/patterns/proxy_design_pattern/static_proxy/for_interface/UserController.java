package com.design.patterns.proxy_design_pattern.static_proxy.for_interface;

import com.design.patterns.proxy_design_pattern.static_proxy.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: cdc
 * @Date: 2020/3/30 11:27
 * @Description:
 */
public class UserController implements IUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserVo login(String telephone, String password) {
        logger.info("执行登录逻辑=============");
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        logger.info("执行注册逻辑=============");
        return null;
    }
}
