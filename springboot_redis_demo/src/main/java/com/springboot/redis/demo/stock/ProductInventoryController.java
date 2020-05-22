package com.springboot.redis.demo.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 15:10
 * @Description:
 */
@RestController
public class ProductInventoryController {

    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;
    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * 更新商品库存
     */
    @RequestMapping("/updateProductInventory")
    @ResponseBody
    public void updateProductInventory(@RequestBody ProductInventory productInventory) {
        //Response response = null;

        try {
            Request request = new ProductInventoryDBUpdateRequest(
                    productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
          //  response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //response = new Response(Response.FAILURE);
        }

        //return response;
    }

    /**
     * 获取商品库存
     */
    @RequestMapping(value = "/getProductInventory/{productId}",method = RequestMethod.GET)
    @ResponseBody
    public ProductInventory getProductInventory(@PathVariable("productId") Integer productId) {

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 1000; i++) {
            int tempt = (int)(Math.random()*101);
            int id = productId + tempt;
            System.out.println("===============商品id====="+id);
            executorService.submit(() -> {
                getDate(id);
            });
        }
        executorService.shutdown();

        return new ProductInventory(productId, -1L);
    }

    private ProductInventory getDate(Integer productId) {
        ProductInventory productInventory = null;
        try {
            Request request = new ProductInventoryCacheRefreshRequest(
                    productId, productInventoryService);
            requestAsyncProcessService.process(request);

            // 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
            // 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;

            // 等待超过200ms没有从缓存中获取到结果
            while(true) {
                if(waitTime > 200) {
                    break;
                }

                // 尝试去redis中读取一次商品库存的缓存数据
                productInventory = productInventoryService.getProductInventoryCache(productId);

                // 如果读取到了结果，那么就返回
                if(productInventory != null) {
                    return productInventory;
                }

                // 如果没有读取到结果，那么等待一段时间
                else {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }

            // 直接尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if(productInventory != null) {
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
