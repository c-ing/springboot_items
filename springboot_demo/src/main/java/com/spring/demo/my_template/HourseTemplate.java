package com.spring.demo.my_template;

import java.io.IOException;

/**
 * @Auther: cdc
 * @Date: 2020/6/5 11:10
 * @Description:
 */
public abstract class HourseTemplate {

    //这是我们的模板方法，子类不能重写
    public final void buildHouse() {
        buildFoundation();//第一步：建造地基
        buildPillars();//第二步，建造支撑
        buildWalls();//第三步，建造墙
        buildWindows();//第四步，建造窗户
        System.out.println("House is built.");
    }

    //这个步骤可以默认实现,原文是private修饰，那么就是说我规定死啦，这个步骤只能这样实现了，但为了易扩展，还是protected OK点
    protected void buildWindows() {
        System.out.println("Building Glass Windows");
    }

    //被子类实现的方法
    protected abstract void buildWalls();
    protected abstract void buildPillars();

    protected void buildFoundation() {
        System.out.println("Building foundation with cement,iron rods and sand");
    }

}
