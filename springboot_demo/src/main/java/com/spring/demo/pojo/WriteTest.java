package com.spring.demo.pojo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/9/24 14:51
 * @Description:
 */
public class WriteTest {

    public static void tableWrite(ServletOutputStream outputStream) {
        String fileName = "F:\\" + "tableWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里直接写多个table的案例了，如果只有一个 也可以直一行代码搞定，参照其他案例
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, PilebodycheckMonthDto.class).registerWriteHandler(new MonthSheetWriteHandler()).relativeHeadRowIndex(9).build();
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
            // 第一次写入会创建头
            excelWriter.write(data(), writeSheet, writeTable0);

            WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();



            // 第二次写入也会创建头，然后在第一次的后面写入数据
            excelWriter.write(data(), writeSheet, writeTable1);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }public static void tableWrite2(ServletOutputStream outputStream) {
        String fileName = "F:\\" + "tableWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里直接写多个table的案例了，如果只有一个 也可以直一行代码搞定，参照其他案例
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).registerWriteHandler(new MonthSheetWriteHandler()).relativeHeadRowIndex(9).build();
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            WriteTable writeTable0 = EasyExcel.writerTable(0).head(DemoData.class).needHead(Boolean.TRUE).build();
            WriteTable writeTable1 = EasyExcel.writerTable(1).head(PilebodycheckMonthDto.class).needHead(Boolean.TRUE).build();
            // 第一次写入会创建头
            excelWriter.write(data(), writeSheet, writeTable0);
            // 第二次写入也会创建头，然后在第一次的后面写入数据
            excelWriter.write(data2(), writeSheet, writeTable1);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    private static List<PilebodycheckMonthDto> data2() {
        List<PilebodycheckMonthDto> list = new ArrayList<PilebodycheckMonthDto>();
        for (int i = 0; i < 3; i++) {
            PilebodycheckMonthDto data = new PilebodycheckMonthDto();
            data.setOrderNum("s");
            data.setAddress("s");
            data.setAreastr("s");
            data.setHeightstr("s");
            data.setName("s");
            list.add(data);
        }
        return list;
    }
}
