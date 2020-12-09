package com.java.one.hundred.session21.serviceimpl;

import com.java.one.hundred.session21.AbstractCart;
import com.java.one.hundred.session21.Item;
import com.java.one.hundred.session21.service.NormalUserCartService;
import com.java.one.hundred.session21.service.OtherUserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/12/4 16:00
 * @Description:
 */
@Service(value = "NormalUserCart")
public class NormalUserCartServiceImpl extends AbstractCart implements NormalUserCartService{

    @Autowired
    private OtherUserCartService otherUserCartService;

    @Transactional
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
        this.getOrderById(1);
    }

    @Transactional
    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(item.getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()))
                .multiply(new BigDecimal("0.1")));
    }

    @Transactional
    @Override
    public void getOrderById(Integer id) {
        System.out.println(String.format("根据id获取订单，{%s}", id));
        Map<Long, Integer> map = new HashMap<>();
        map.put((long)1, 2);
        otherUserCartService.getById(1);

       // this.process(id, map);
    }
}
