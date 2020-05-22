package com.springboot.redis.demo.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:52
 * @Description:
 */
public class ProductInventoryCacheRefreshRequest implements Request {

    private static final Logger logger = LoggerFactory.getLogger(ProductInventoryCacheRefreshRequest.class);

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    private boolean forceRfresh = false;


    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService, boolean forceRfresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRfresh = forceRfresh;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        logger.info("====================更新缓存");
        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventory.setProductId(productId);
        productInventoryService.setProductInventoryCache(productInventory);
    }


    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public boolean isForceRfresh() {
        return forceRfresh;
    }
}
