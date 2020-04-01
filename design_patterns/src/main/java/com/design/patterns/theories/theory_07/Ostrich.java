package com.design.patterns.theories.theory_07;

/**
 * @Auther: cdc
 * @Date: 2020/4/1 11:33
 * @Description: 鸵鸟类
 */
public class Ostrich implements Tweetable,EggLayable{

    TweetAbility tweetAbility = new TweetAbility(); //组合
    EggAbility eggAbility = new EggAbility(); // 组合

    @Override
    public void egg() {
        eggAbility.egg(); // 委托
    }

    @Override
    public void tweet() {
        tweetAbility.tweet(); // 委托
    }
}
