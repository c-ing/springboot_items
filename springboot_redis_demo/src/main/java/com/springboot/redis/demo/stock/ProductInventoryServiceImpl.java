package com.springboot.redis.demo.stock;

import com.springboot.redis.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:50
 * @Description:
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{

   // @Resource
   // private ProductInventoryMapper productInventoryMapper;
    //@Resource
    //private RedisDAO redisDAO;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 移除redis中商品缓存
     * @param productInventory 商品库存
     */
    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
       // redisDAO.delete(key);
        redisUtil.del(key);
    }
    /**
     * 更新数据库中库存
     * @param productInventory 商品库存
     */
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
       // productInventoryMapper.updateProductInventory(productInventory);
    }



    /**
     * 根据商品id查询商品库存
     * @param productId 商品id
     * @return 商品库存
     */
    public ProductInventory findProductInventory(Integer productId) {
        return null;//productInventoryMapper.findProductInventory(productId);
    }

    /**
     * 设置redis中商品库存的缓存
     * @param productInventory 商品库存
     */
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisUtil.set(key, String.valueOf(productInventory.getInventoryCnt()));
    }


    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;

        String key = "product:inventory:" + productId;
        String result = (String)redisUtil.get(key);

        if(result != null && !"".equals(result)) {
            try {
                inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
