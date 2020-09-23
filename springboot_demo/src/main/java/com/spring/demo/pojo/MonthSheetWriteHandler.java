package com.spring.demo.pojo;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;

/**
 * @Auther: cdc
 * @Date: 2020/9/23 18:03
 * @Description:
 */
public class MonthSheetWriteHandler implements SheetWriteHandler {

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 500);
        Cell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("附件2");

        //设置标题
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) 800);
        Cell cell1 = row2.createCell(0);
        cell1.setCellValue("企业对账函-函证账户余额及发票");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 400);
        cellStyle.setFont(font);
        cell1.setCellStyle(cellStyle);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 0, 4));

        Row row3 = sheet.createRow(2);
        row3.setHeight((short)500);
        Cell cell3 = row3.createCell(0);
        cell3.setCellValue("致：福州青和食品配送有限公司");
        Font fontWithRow3 = workbook.createFont();
        fontWithRow3.setColor(IndexedColors.RED.index);
        fontWithRow3.getColor();
        CellStyle cellStyleWithRow3 = workbook.createCellStyle();
        cellStyleWithRow3.setFont(fontWithRow3);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(2, 2, 0, 4));

        for(int i=3;i<7; i++){
            Row rowTempt = sheet.createRow(7);
            rowTempt.setHeight((short)500);
            sheet.addMergedRegionUnsafe(new CellRangeAddress(i, i, 0, 4));
        }

        Row row8 = sheet.createRow(7);
        row8.setHeight((short)500);
        Cell cell8 = row8.createCell(0);
        cell8.setCellValue("本公司与贵公司的往来账项列示如下：");
        sheet.addMergedRegionUnsafe(new CellRangeAddress(7, 7, 0, 4));



        //设置填表日期,填报人,联系方式
        Row row9 = sheet.createRow(8);
        row9.setHeight((short) 500);
        row9.createCell(0).setCellValue("对账日期");

    }
}
