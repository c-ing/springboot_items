package com.spring.demo.pojo;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.spring.demo.service.UserService;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: cdc
 * @Date: 2020/9/23 18:03
 * @Description:
 */
@Component
public class MonthSheetWriteHandler implements SheetWriteHandler {

    @Autowired
    private UserService userService;

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
        cell3.setCellValue("致：福州青和食品配送有限公司");
        Font fontWithRow3 = workbook.createFont();
        fontWithRow3.setColor(IndexedColors.RED.index);
        CellStyle cellStyleWithRow3 = workbook.createCellStyle();
        cellStyleWithRow3.setFont(fontWithRow3);
        cell3.setCellStyle(cellStyleWithRow3);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(2, 2, 1, 5));

        for(int i=3;i<7; i++){
            Row rowTempt = sheet.createRow(i);
            rowTempt.setHeight((short)500);
            sheet.addMergedRegionUnsafe(new CellRangeAddress(i, i, 1, 5));
        }

        Row row8 = sheet.createRow(7);
        row8.setHeight((short)500);
        Cell cell8 = row8.createCell(1);
        cell8.setCellValue("本公司与贵公司的往来账项列示如下：");
        sheet.addMergedRegionUnsafe(new CellRangeAddress(7, 7, 1, 5));



        //设置填表日期,填报人,联系方式
        Row row9 = sheet.createRow(8);
        row9.setHeight((short) 500);
        row9.createCell(1).setCellValue("对账期间");

       for(int i=9;i<13; i++){
            Row rowTempt = sheet.createRow(i);
            rowTempt.setHeight((short)500);
            rowTempt.createCell(1);
            rowTempt.createCell(2);
            rowTempt.createCell(3);
            rowTempt.createCell(4);
            rowTempt.createCell(5);
        }

        Row row16 = sheet.createRow(15);
        row16.setHeight((short) 500);
        row16.createCell(1).setCellValue("2、其他事项");

        Row row17 = sheet.createRow(16);
        row17.setHeight((short) 500);
        row17.createCell(1).setCellValue("    贵公司欠本公司发票     元");
        sheet.addMergedRegionUnsafe(new CellRangeAddress(16, 16, 1, 5));

        Row row18 = sheet.createRow(17);
        row18.setHeight((short) 500);
        row18.createCell(1).setCellValue("3、本函仅为复核账目之用，并非催款结算。若款项在上述日期之后已经结清，仍请及时函复为盼。");
        sheet.addMergedRegionUnsafe(new CellRangeAddress(17, 17, 1, 5));

        Row row24 = sheet.createRow(23);
        row24.createCell(1).setCellValue("结论：");
        CellStyle resultCellStyle = workbook.createCellStyle();
        Font resultFont = workbook.createFont();
        resultFont.setBold(true);
        resultFont.setFontHeight((short) 250);
        resultCellStyle.setFont(resultFont);
        row24.getCell(1).setCellStyle(resultCellStyle);

        Row row25 = sheet.createRow(24);
        row25.createCell(1).setCellValue("1.信息证明无误");
        row25.createCell(2);
        row25.createCell(3).setCellValue("2.信息不符，请列明不符项目及具体内容");
        row25.createCell(4);
        row25.createCell(5);

        Row row26 = sheet.createRow(25);
        row26.createCell(1);
        row26.createCell(2);
        row26.createCell(3);
        row26.createCell(4);
        row26.createCell(5);
        Row row27 = sheet.createRow(26);
        row27.createCell(1);
        row27.createCell(2);
        row27.createCell(3);
        row27.createCell(4);
        row27.createCell(5);
        Row row28 = sheet.createRow(27);
        row28.createCell(1);
        row28.createCell(2);
        row28.createCell(3);
        row28.createCell(4);
        row28.createCell(5);
        Row row30 = sheet.createRow(29);
        row30.createCell(1);
        row30.createCell(2);
        row30.createCell(3);
        row30.createCell(4);
        row30.createCell(5);

        Row row29 = sheet.createRow(28);
        row29.createCell(1).setCellValue("（盖章）");
        row29.createCell(2);
        row29.createCell(3);
        row29.createCell(4).setCellValue("（盖章）");
        row29.createCell(5);

     //   sheet.addMergedRegionUnsafe(new CellRangeAddress(28, 28, 1, 2));

        Row row31 = sheet.createRow(30);
        row31.createCell(1);
        Cell cell2ForRow31 = row31.createCell(2);
        cell2ForRow31.setCellValue("年    月    日");
        row31.createCell(3);
        row31.createCell(4);
        row31.createCell(5).setCellValue("年    月    日");


      //  sheet.addMergedRegionUnsafe(new CellRangeAddress(24, 30, 3, 5));

        CellStyle cellStyleWithBorderTop = workbook.createCellStyle();
        cellStyleWithBorderTop.setBorderTop(BorderStyle.MEDIUM);
        row25.getCell(1).setCellStyle(cellStyleWithBorderTop);


        this.buildResultStyle(row25,sheet,workbook);

    }

    private void buildResultStyle(Row row,Sheet sheet,Workbook workbook) {

        CellStyle cellStyleTop1 = workbook.createCellStyle();
        cellStyleTop1.setBorderTop(BorderStyle.MEDIUM);
        cellStyleTop1.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleTop1.setBorderRight(BorderStyle.NONE);

        CellStyle cellStyleTop2 = workbook.createCellStyle();
        cellStyleTop2.setBorderTop(BorderStyle.MEDIUM);
        cellStyleTop2.setBorderRight(BorderStyle.MEDIUM);

        CellStyle cellStyleTop3 = workbook.createCellStyle();
        cellStyleTop3.setBorderTop(BorderStyle.MEDIUM);
        cellStyleTop3.setBorderRight(BorderStyle.NONE);

        CellStyle cellStyleTop5 = workbook.createCellStyle();
        cellStyleTop5.setBorderTop(BorderStyle.MEDIUM);
        cellStyleTop5.setBorderRight(BorderStyle.MEDIUM);

        CellStyle cellStyleBot1 = workbook.createCellStyle();
        cellStyleBot1.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBot1.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleBot1.setBorderRight(BorderStyle.NONE);

        CellStyle cellStyleBot2 = workbook.createCellStyle();
        cellStyleBot2.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBot2.setBorderRight(BorderStyle.MEDIUM);
        cellStyleBot2.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle cellStyleBot3 = workbook.createCellStyle();
        cellStyleBot3.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBot3.setBorderRight(BorderStyle.NONE);

        CellStyle cellStyleBot5 = workbook.createCellStyle();
        cellStyleBot5.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBot5.setBorderRight(BorderStyle.MEDIUM);
        cellStyleBot5.setAlignment(HorizontalAlignment.RIGHT);


        CellStyle cellStyleCom1 = workbook.createCellStyle();
        cellStyleCom1.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleCom1.setAlignment(HorizontalAlignment.CENTER);

        CellStyle cellStyleCom4 = workbook.createCellStyle();
        cellStyleCom4.setAlignment(HorizontalAlignment.CENTER);

        CellStyle cellStyleCom5 = workbook.createCellStyle();
        cellStyleCom5.setBorderRight(BorderStyle.MEDIUM);

        int lastRowNum = sheet.getLastRowNum();

        int rowNum = row.getRowNum();
        for (int i = 0; i < 8; i++) {
            if (i > 0) {
                row = sheet.getRow(rowNum + i);
            }
            if (row == null) {
                continue;
            }
            for (int cellIndex = 1; cellIndex < 6; cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                if (cell == null) {
                    continue;
                }
                if (rowNum == row.getRowNum()) {

                    switch (cellIndex){
                        case 1:
                            cell.setCellStyle(cellStyleTop1);
                            break;
                        case 2:
                            cell.setCellStyle(cellStyleTop2);
                            break;
                        case 3:
                            cell.setCellStyle(cellStyleTop3);
                            break;
                        case 4:
                            cell.setCellStyle(cellStyleTop3);
                            break;
                        case 5:
                            cell.setCellStyle(cellStyleTop5);
                            break;
                        default:
                            break;
                    }

                } else if (lastRowNum == row.getRowNum()) {
                    switch (cellIndex) {
                        case 1:
                            cell.setCellStyle(cellStyleBot1);
                            break;
                        case 2:
                            cell.setCellStyle(cellStyleBot2);
                            break;
                        case 3:
                            cell.setCellStyle(cellStyleBot3);
                            break;
                        case 4:
                            cell.setCellStyle(cellStyleBot3);
                            break;
                        case 5:
                            cell.setCellStyle(cellStyleBot5);
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (cellIndex){
                        case 1:
                            cell.setCellStyle(cellStyleCom1);
                            break;
                        case 3:
                            cell.setCellStyle(cellStyleCom1);
                            break;
                        case 4:
                            cell.setCellStyle(cellStyleCom4);
                            break;
                        case 5:
                            cell.setCellStyle(cellStyleCom5);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }
}
