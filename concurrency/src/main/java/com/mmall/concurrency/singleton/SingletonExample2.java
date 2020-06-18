package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时创建
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2() {

    }

    // 单利对象
    private static final SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
