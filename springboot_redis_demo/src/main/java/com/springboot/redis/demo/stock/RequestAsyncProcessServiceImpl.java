package com.springboot.redis.demo.stock;

import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 15:02
 * @Description:
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request) {
        try {
            // 做请求的路由，根据每个请求的商品id，路由到对应的内存队列中去
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
            // 将请求放入对应的队列中，完成路由操作，put:当Queue已经满了时，会进入等待，只要不被中断，就会插入数据到队列中。会阻塞，可以响应中断
            queue.put(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路由到的内存队列
     * @param productId 商品id
     * @return 内存队列
     */
    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
        //通过RequestQueue获取内存队列。
        //因为在初始化线程池的时候，已经调用过一个生成过了，单例模式，所以这里拿到的还是启动时生成的内存队列。
        //并且也是线程池中的内存队列，所以当这里拿到队列并且在队列中设置了参数，线程池中的队列就能做出对应操作，
        // 因为线程池一直在阻塞中执行。
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        // 对hash值取模，将hash值路由到指定的内存队列中，比如内存队列大小 8
        // 用内存队列的数量对hash值取模之后，结果一定是在0~7之间
        // 所以任何一个商品id都会被固定路由到同样的一个内存队列中去的
        int index = (requestQueue.queueSize() - 1) & hash;

        return requestQueue.getQueue(index);
    }
}
