package com.design.patterns.factory_design_pattern.simple_factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:34
 * @Description: 在RuleConfigParserFactory实现中，我们每次调用 RuleConfigParserFactory 的 createParser() 的时
 * 候，都要创建一个新的 parser。实际上，如果 parser 可以复用，为了节省内存和对象创建
 * 的时间，我们可以将 parser 事先创建好缓存起来。当调用 createParser() 函数的时候，我
 * 们从缓存中取出 parser 对象直接使用。
 */
public class RuleConfigParserFactory2 {

    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static{
        cachedParsers.put("json", new JsonRuleConfigParser());
       // cachedParsers.put("xml", new XmlRuleConfigParser());
       //  cachedParsers.put("yaml", new YamlRuleConfigParser());
       // cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }

}
