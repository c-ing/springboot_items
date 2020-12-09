package com.java.one.hundred.session21;

import com.java.one.hundred.session21.service.NormalUserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/12/4 16:06
 * @Description:
 */
@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private NormalUserCartService normalUserCartService;

    @GetMapping("right")
    @ResponseBody
    public Cart right(@RequestParam("userId")int userId) {
        String userCategory = "Normal";//Db.getUserCategory(userId);
       // normalUserCartService.getOrderById(1);
        AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
        Map<Long, Integer> map = new HashMap<>();
        map.put((long)1, 2);
        normalUserCartService.getOrderById(1);

        return new Cart();//cart.process(userId, map);
    }

}
