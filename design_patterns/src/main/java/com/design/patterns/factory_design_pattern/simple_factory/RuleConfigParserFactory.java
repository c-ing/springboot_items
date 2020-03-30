package com.design.patterns.factory_design_pattern.simple_factory;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:22
 * @Description: 这个类只负责对象的创建。而这个类就是我们现在要讲的简单工厂模式类。
 */
public class RuleConfigParserFactory {

    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = null;//new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = null;//new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = null;//new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = null;//new PropertiesRuleConfigParser();
        }
        return parser;
    }

}
