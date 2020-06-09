package com.java.items.demo.callback;

/**
 * @Auther: cdc
 * @Date: 2020/6/1 11:00
 * @Description:
 */
public class HappyPeople {

    public void celebrateSpringFestival(Travel travel) {
        //买票
        buyTicket();
        //坐车
        travel.travelMethod();
        //团圆
        celebreate();
    }

    //买票
    protected final void buyTicket() {
        System.out.println("Buying ticket .....");
    }

    //团圆
    protected final void celebreate() {
        System.out.println("Happy New Year with family .....");
    }

    public static void main(String[] args) {
        HappyPeople happyPeople = new HappyPeople();
        happyPeople.celebrateSpringFestival(() -> {
            //TODO Auto-generated method stub
            System.out.println("Travelling by coach.....");
        });
    }
}
