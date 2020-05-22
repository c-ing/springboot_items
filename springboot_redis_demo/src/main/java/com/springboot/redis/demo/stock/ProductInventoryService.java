package com.springboot.redis.demo.stock;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:50
 * @Description:
 */
public interface ProductInventoryService {

    public void removeProductInventoryCache(ProductInventory productInventory);

    public void updateProductInventory(ProductInventory productInventory);

    public ProductInventory findProductInventory(Integer productId);

    public void setProductInventoryCache(ProductInventory productInventory);

    public ProductInventory getProductInventoryCache(Integer productId);
}
