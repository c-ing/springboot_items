package com.spring.demo.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
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
@ContentRowHeight((short) 35)
public class PilebodycheckMonthDto {

    @ColumnWidth(20)
    @ExcelProperty(value = "项目",index = 1)
    private String orderNum;

    @ColumnWidth(20)
    @ExcelProperty(value = "当月货物发生额",index = 2)
    private String name;

    @ColumnWidth(20)
    @ExcelProperty(value = "当月付款金额",index = 3)
    private String address;

    @ColumnWidth(20)
    @ExcelProperty(value = "当月开票金额",index = 4)
    private String areastr;

    @ColumnWidth(30)
    @ExcelProperty(value = "未付货款结余（正数为我司欠贵司货款。负数为我司预付贵司货款）",index = 5)
    private String heightstr;

}
