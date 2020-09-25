package com.spring.demo.pojo;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * @Auther: cdc
 * @Date: 2020/9/25 9:35
 * @Description:
 */
public class CustomerRowWriteHandler implements RowWriteHandler {
    @Override
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer integer, Boolean aBoolean) {
        System.out.println("-----------------------");
        System.out.println(row.getRowNum());
        System.out.println(integer);
        System.out.println(aBoolean);
        System.out.println("-----------------------");


        if (integer == 2) {
            Sheet sheet = writeSheetHolder.getSheet();
            Workbook workbook = sheet.getWorkbook();
            Row row16 = sheet.createRow(15);
            row16.setHeight((short) 500);
            row16.createCell(1).setCellValue("2、其他事项");

            Row row17 = sheet.createRow(16);
            row17.setHeight((short) 500);
            row17.createCell(1).setCellValue("    贵公司欠本公司发票     元");
            sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 5));

            Row row18 = sheet.createRow(17);
            row18.setHeight((short) 500);
            row18.createCell(1).setCellValue("3、本函仅为复核账目之用，并非催款结算。若款项在上述日期之后已经结清，仍请及时函复为盼。");
            sheet.addMergedRegion(new CellRangeAddress(17, 17, 1, 5));

            CellStyle yearMothDateCellStyle = workbook.createCellStyle();
            yearMothDateCellStyle.setAlignment(HorizontalAlignment.RIGHT);

            Row row22 = sheet.createRow(21);
            row22.setHeight((short)500);
            row22.createCell(5).setCellValue("年     月      日");
            row22.getCell(5).setCellStyle(yearMothDateCellStyle);



            Row row24 = sheet.createRow(23);
            row24.createCell(1).setCellValue("结论：");
            CellStyle resultCellStyle = workbook.createCellStyle();
            Font resultFont = workbook.createFont();
            resultFont.setBold(true);
            resultFont.setFontHeight((short) 250);
            resultCellStyle.setFont(resultFont);
            row24.getCell(1).setCellStyle(resultCellStyle);

            for (int i = 24; i <= 32; i++) {
                Row rowTempt = sheet.createRow(i);
                rowTempt.createCell(1);
                rowTempt.createCell(2);
                rowTempt.createCell(3);
                rowTempt.createCell(4);
                rowTempt.createCell(5);
            }

            Row row25 = sheet.getRow(24);
            row25.getCell(1).setCellValue("1.信息证明无误");
            row25.getCell(3).setCellValue("2.信息不符，请列明不符项目及具体内容");

            Row row29 = sheet.getRow(28);
            row29.getCell(1).setCellValue("（盖 章）");
            row29.getCell(4).setCellValue("（盖 章）");
            row29.getCell(1).setCellStyle(yearMothDateCellStyle);
            row29.getCell(4).setCellStyle(yearMothDateCellStyle);

            Row row31 = sheet.getRow(30);
            row31.getCell(2).setCellValue("年     月      日");
            row31.getCell(5).setCellValue("年     月      日");
            row31.getCell(2).setCellStyle(yearMothDateCellStyle);
            row31.getCell(5).setCellStyle(yearMothDateCellStyle);


            RegionUtil.setBorderTop(BorderStyle.MEDIUM,new CellRangeAddress(24,32,1,5),sheet);
            RegionUtil.setBorderLeft(BorderStyle.MEDIUM,new CellRangeAddress(24,32,1,1),sheet);
            RegionUtil.setBorderRight(BorderStyle.MEDIUM,new CellRangeAddress(24,32,2,2),sheet);
            RegionUtil.setBorderRight(BorderStyle.MEDIUM,new CellRangeAddress(24,32,5,5),sheet);
            RegionUtil.setBorderBottom(BorderStyle.MEDIUM,new CellRangeAddress(24,32,1,5),sheet);

          //  this.buildResultStyle(row25, sheet, workbook);

        }

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
        cellStyleCom1.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle cellStyleCom4 = workbook.createCellStyle();
        cellStyleCom4.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle cellStyleCom5 = workbook.createCellStyle();
        cellStyleCom5.setBorderRight(BorderStyle.MEDIUM);

        int lastRowNum = sheet.getLastRowNum();

        int rowNum = row.getRowNum();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
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
                        case 2:
                            cell.setCellStyle(cellStyleCom4);
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
