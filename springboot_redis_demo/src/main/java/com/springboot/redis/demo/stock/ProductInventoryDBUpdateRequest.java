package com.springboot.redis.demo.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:43
 * @Description:
 */
public class ProductInventoryDBUpdateRequest implements Request {

   private ProductInventory productInventory;

   private ProductInventoryService productInventoryService;

    private Logger log = LoggerFactory.getLogger(getClass());

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 删除redis中的缓存
        productInventoryService.removeProductInventoryCache(productInventory);

        //        log.info("写请求：模拟写耗时操作，休眠 10 秒钟");
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 修改数据库中的库存
        productInventoryService.updateProductInventory(productInventory);
        log.info("============删除缓存，更新数据库");
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }

    @Override
    public boolean isForceRfresh() {
        return false;
    }
}
