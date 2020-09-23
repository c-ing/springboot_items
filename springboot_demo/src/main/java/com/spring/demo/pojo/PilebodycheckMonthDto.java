package com.spring.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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


    @ColumnWidth(28)
    @ExcelProperty(value = "项目",index = 0)
    private String orderNum;

    @ColumnWidth(28)
    @ExcelProperty(value = "当月货物发生额",index = 1)
    private String name;

    @ColumnWidth(28)
    @ExcelProperty(value = "当月付款金额",index = 2)
    private String address;

    @ColumnWidth(28)
    @ExcelProperty(value = "当月开票金额",index = 3)
    private String areastr;

    @ColumnWidth(28)
    @ExcelProperty(value = "未付货款结余（正数为我司欠贵司货款。负数为我司预付贵司货款）",index = 4)
    private String heightstr;

}
