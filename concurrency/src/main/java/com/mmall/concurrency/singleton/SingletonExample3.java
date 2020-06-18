package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {

    }

    // 单利对象
    private static final SingletonExample3 instance = null;

    // 静态工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            return new SingletonExample3();
        }
        return instance;
    }
}
