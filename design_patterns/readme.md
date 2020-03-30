


44_工厂模式（上）
    simple_factor:简单工厂
    factory_method:工厂方法
    Abstract Factory：抽象工厂
    
   总结一下:
       尽管简单工厂模式的代码实现中，有多处 if 分支判断逻辑，违背开闭原则，但
        权衡扩展性和可读性，这样的代码实现在大多数情况下（比如，不需要频繁地添加
        parser，也没有太多的 parser）是没有问题的。