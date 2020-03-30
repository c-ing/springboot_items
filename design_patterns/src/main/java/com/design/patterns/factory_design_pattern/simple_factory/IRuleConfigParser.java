package com.design.patterns.factory_design_pattern.simple_factory;

/**
 * @Auther: cdc
 * @Date: 2020/3/26 10:23
 * @Description:
 */
public interface IRuleConfigParser {

    RuleConfig parse(String configText);
}
