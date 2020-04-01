package com.design.patterns.proxy_design_pattern.static_proxy.for_interface;

import com.design.patterns.proxy_design_pattern.static_proxy.UserVo;

/**
 * @Auther: cdc
 * @Date: 2020/3/30 11:25
 * @Description:
 */
public interface IUserController {

    UserVo login(String telephone, String password);
    UserVo register(String telephone, String password);
}
