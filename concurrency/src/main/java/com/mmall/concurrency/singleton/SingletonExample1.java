package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时创建
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1() {

    }

    // 单利对象
    private static final SingletonExample1 instance = null;

    // 静态工厂方法
    public static SingletonExample1 getInstance() {
        // 此处线程不安全
        if (instance == null) {
            return new SingletonExample1();
        }
        return instance;
    }
}
