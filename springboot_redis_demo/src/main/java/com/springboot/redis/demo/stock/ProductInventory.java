package com.springboot.redis.demo.stock;

import java.io.Serializable;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:47
 * @Description:
 */
public class ProductInventory implements Serializable {

    private Integer productId;

    private Long inventoryCnt;

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
