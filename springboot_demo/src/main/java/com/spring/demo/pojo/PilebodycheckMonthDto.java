package com.spring.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther: cdc
 * @Date: 2020/9/23 17:46
 * @Description:
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PilebodycheckMonthDto {


    @ExcelProperty(value = {"序号","序号"},index = 0)
    private String orderNum;

    @ExcelProperty(value = {"堆体名称","堆体名称"},index = 1)
    private String name;

    @ExcelProperty(value = {"具体位置","具体位置"},index = 3)
    private String address;

    @ExcelProperty(value = {"占地面积（平方）","占地面积（平方）"},index = 4)
    private String areastr;

    @ExcelProperty(value = {"堆体高度（米）","堆体高度（米）"},index = 5)
    private String heightstr;

    @ExcelProperty(value = {"建筑垃圾堆存量（万方）","建筑垃圾堆存量（万方）"},index = 6)
    private String stocknum;

    @ExcelProperty(value = {"治理方式","治理方式"},index = 7)
    private String governwayname;

    @ExcelProperty(value = {"如需外运，计划外运时间","如需外运，计划外运时间"},index = 8)
    private String outwardtransporttime;

    @ExcelProperty(value = {"截止目前累计治理量（万方）","截止目前累计治理量（万方）"},index = 13)
    private String governnum;

    @ExcelProperty(value = {"治理主体","治理主体"},index = 14)
    private String governbody;


    @ExcelProperty(value = {"堆体类型","堆体类型"},index = 2)
    private String typestrname;


    @ExcelProperty(value = {"监管单位","监管单位"},index = 15)
    private String supervisedepartname;

    @ExcelProperty(value = {"监管责任人","监管责任人"},index = 16)
    private String supervisepeoname;

    @ExcelProperty(value = {"职务","职务"},index = 17)
    private String supervisepeoposition;

    @ExcelProperty(value = {"联系方式","联系方式"},index = 18)
    private String supervisepeophone;

    @ExcelProperty(value = {"本月治理量（万方）","外运量"},index = 9)
    private String outwardtransportnum;

    @ExcelProperty(value = {"本月治理量（万方）","整理地形绿化量"},index = 10)
    private String afforestnum;

    @ExcelProperty(value = {"本月治理量（万方）","临时覆盖或绿化量"},index = 11)
    private String temporarilynum ;

    @ExcelProperty(value = {"本月治理量（万方）","合计"},index = 12)
    private String goverytotal;

}
