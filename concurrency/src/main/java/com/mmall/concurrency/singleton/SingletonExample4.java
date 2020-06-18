package com.mmall.concurrency.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时创建
 */
@ThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {

    }

    // 1. memory = allocate() 分配对象的内存空间
    // 2. ctorInstance() 初始化对象
    // 3. instance = memory 设置instance指向刚分配的内存
    // 多线程指令重排的问题

    // 单利对象
    private static final SingletonExample4 instance = null;

    // 静态工厂方法
    public static  SingletonExample4 getInstance() {
        if (instance == null) {// 双重检测机制
            synchronized (SingletonExample4.class) { //同步锁
                if (instance == null) {
                    return new SingletonExample4();
                }
            }

        }
        return instance;
    }
}
