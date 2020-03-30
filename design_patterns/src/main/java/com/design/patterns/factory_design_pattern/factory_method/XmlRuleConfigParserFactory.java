package com.design.patterns.factory_design_pattern.factory_method;

import com.design.patterns.factory_design_pattern.simple_factory.IRuleConfigParser;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:45
 * @Description:
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {

    @Override
    public IRuleConfigParser createParser() {
        return null;
    }
}
