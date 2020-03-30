package com.design.patterns.factory_design_pattern.factory_method;

import com.design.patterns.factory_design_pattern.simple_factory.IRuleConfigParser;
import com.design.patterns.factory_design_pattern.simple_factory.JsonRuleConfigParser;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:44
 * @Description:
 */
public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory{

    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
