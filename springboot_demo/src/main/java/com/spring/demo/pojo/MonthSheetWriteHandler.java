package com.spring.demo.pojo;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.spring.demo.service.UserService;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: cdc
 * @Date: 2020/9/23 18:03
 * @Description:
 */

@Data
public class MonthSheetWriteHandler implements SheetWriteHandler {

    private String supplierName;
    private String cityCompanyName;


    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 500);

        //设置标题
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) 800);
        Cell cell1 = row2.createCell(1);
        cell1.setCellValue("企业对账函-函证账户余额及发票");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight((short) 400);
        cellStyle.setFont(font);
        cell1.setCellStyle(cellStyle);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 1, 5));

        Row row3 = sheet.createRow(2);
        row3.setHeight((short)500);
        Cell cell3 = row3.createCell(1);
        cell3.setCellValue("致："+supplierName);
        Font fontWithRow3 = workbook.createFont();
       // fontWithRow3.setColor(IndexedColors.RED.index);
        fontWithRow3.setBold(true);
        CellStyle cellStyleWithRow3 = workbook.createCellStyle();
        cellStyleWithRow3.setFont(fontWithRow3);
        cell3.setCellStyle(cellStyleWithRow3);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(2, 2, 1, 5));

        for(int i=3;i<8; i++){
            Row rowTempt = sheet.createRow(i);
            rowTempt.setHeight((short)500);
            rowTempt.createCell(1);
           // sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 5));
        }


        String beginContent = "    下列信息出自" + cityCompanyName + "（以下简称“本公司”）账面记录，" +
                "如与贵公司记录相符，请在本函下端“信息证明无误”处盖章（公章或财务专用章）；" +
                "如有不符，请在“信息不符”处列明不符项目。如存在与本公司有关的未列入本函的其他项目，" +
                "也请在“信息不符”处列出这些项目的金额及详细资料，并盖章确认。";
        sheet.addMergedRegion(new CellRangeAddress(3, 6, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 5));

        CellStyle cellStyleWithRow4 = workbook.createCellStyle();
        cellStyleWithRow4.setWrapText(true);
        Font fontWithRow4 = workbook.createFont();
        fontWithRow4.setFontHeightInPoints((short) 12);
        cellStyleWithRow4.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleWithRow4.setFont(fontWithRow4);
        cellStyleWithRow4.setShrinkToFit(true);

        Cell cellWithRow4 = sheet.getRow(3).getCell(1);
        cellWithRow4.setCellStyle(cellStyleWithRow4);
        cellWithRow4.setCellValue(beginContent);

        String contentForRow7 = "    请贵公司将回函扫描发邮箱或者直接寄至本公司，谢谢！";
        sheet.getRow(7).getCell(1).setCellStyle(cellStyleWithRow4);
        sheet.getRow(7).getCell(1).setCellValue(contentForRow7);


        Row row9 = sheet.createRow(8);
        row9.setHeight((short)500);
        Cell cell9 = row9.createCell(1);
        cell9.setCellValue("本公司与贵公司的往来账项列示如下：");
        cell9.setCellStyle(cellStyleWithRow4);
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 5));



        //设置填表日期,填报人,联系方式
        Row row10 = sheet.createRow(9);
        row10.setHeight((short) 500);
        row10.createCell(1).setCellValue("对账期间");
        CellStyle cellStyleWithRow9 = workbook.createCellStyle();
        Font fontWithRow9 = workbook.createFont();
        fontWithRow9.setBold(true);
        cellStyleWithRow9.setFont(fontWithRow9);
        cellStyleWithRow9.setVerticalAlignment(VerticalAlignment.CENTER);
        row10.getCell(1).setCellStyle(cellStyleWithRow9);
        CellRangeAddress region = new CellRangeAddress(9, 9, 1, 5);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderTop(BorderStyle.THIN,region,sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN,region,sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN,region,sheet);

    }

}
