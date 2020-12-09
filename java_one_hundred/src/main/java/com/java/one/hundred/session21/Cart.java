package com.java.one.hundred.session21;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/12/4 15:46
 * @Description:
 */
public class Cart {

    //商品清单
    private List<Item> items = new ArrayList<>();
    //总优惠
    private BigDecimal totalDiscount;
    //商品总价
    private BigDecimal totalItemPrice;
    //总运费
    private BigDecimal totalDeliveryPrice;
    //应付总价
    private BigDecimal payPrice;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(BigDecimal totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public BigDecimal getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }

    public void setTotalDeliveryPrice(BigDecimal totalDeliveryPrice) {
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }
}
