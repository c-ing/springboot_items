package com.design.patterns.factory_design_pattern.factory_method;

import com.design.patterns.factory_design_pattern.simple_factory.IRuleConfigParser;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:43
 * @Description:
 */
public interface IRuleConfigParserFactory {

    IRuleConfigParser createParser();

}
