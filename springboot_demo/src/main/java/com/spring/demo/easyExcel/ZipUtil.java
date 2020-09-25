package com.spring.demo.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.spring.demo.pojo.CustomerRowWriteHandler;
import com.spring.demo.pojo.MonthSheetWriteHandler;
import com.spring.demo.pojo.PilebodycheckMonthDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: cdc
 * @Date: 2020/9/25 15:59
 * @Description:
 */
@Slf4j
public class ZipUtil {


    // 获取当前系统的临时目录
    private static final String FilePath = System.getProperty("java.io.tmpdir") + File.separator;

    public void execute(HttpServletResponse response) {
        System.out.println("===================");
        System.out.println(FilePath);
        // 用于存放文件路径
        List<String> filePaths = new ArrayList<>();
        //生成的ZIP文件名为Demo.zip
        String tmpFileName = "Demo.zip";
        // zip文件路径
        String strZipPath = FilePath + tmpFileName;
        filePaths.add(strZipPath);
        try {
            //创建zip输出流
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            //声明文件集合用于存放excel文件
            List<File> fileList = new ArrayList<File>();
            //生成excel文件集合
            for (int i = 0; i < 10; i++) {
                // 生成随机文件名
                String filename = FilePath + generateRandomFilename() + ".xlsx";
                // 将文件路径保存
                fileList.add(creatFile(filename));
                filePaths.add(filename);
                List<PilebodycheckMonthDto> list = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    // 造一些表格数据，一般是从数据库查出来的list集合数据
                    PilebodycheckMonthDto dataOne = new PilebodycheckMonthDto();
                    if (j == 0) {
                        dataOne.setOrderNum("至上月末");
                    } else if (j == 1) {
                        dataOne.setOrderNum("本月合计");
                    } else {
                        dataOne.setOrderNum("本年累计");
                    }
                    //。。。
                    list.add(dataOne);
                }
                // 使用easyexcel生成excel文件
                writeExcel(fileList.get(i), PilebodycheckMonthDto.class, list,ExcelTypeEnum.XLS);
            }
            byte[] buffer = new byte[1024];
            //将excel文件放入zip压缩包
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                FileInputStream fis = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                //设置压缩文件内的字符编码，不然会变成乱码
                //out.setEncoding("GBK");
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
            //下载zip文件
            this.downFile(response, tmpFileName,filePaths);
        } catch (Exception e) {
            // 下载失败删除生成的文件
            deleteFile(filePaths);
            log.error("文件下载出错", e);
        }
    }

    /**
     * 文件下载
     *  @param response
     * @param str
     * @param filePaths
     */
    private void downFile(HttpServletResponse response, String str, List<String> filePaths) {
        try {
            String path = FilePath + str;
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
                deleteFile(filePaths);
            }
        } catch (IOException e) {
            deleteFile(filePaths);
            log.error("文件下载出错", e);
        }
    }
    //创建文件File对象
    private File creatFile(String filePath) {
        File file = new File(filePath);
        return file;
    }
    //生成随机文件名
    public String generateRandomFilename() {
        String RandomFilename = "";
        Random rand = new Random();//生成随机数
        int random = rand.nextInt();
        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
                String.valueOf(intDay) + "_";

        RandomFilename = now + String.valueOf(random > 0 ? random : (-1) * random);
        return RandomFilename;
    }
    //删除文件
    public static boolean deleteFile(List<String> filePath){
        boolean result = false;
        for (String pathname:filePath){
            File file = new File(pathname);
            if (file.exists()) {
                file.delete();
                result = true;
            }
        }
        return result;
    }
    /**
     * @Title: writeExcel
     * @Description: 写入excel文件到输出流web端
     *
     */
    private void writeExcel(OutputStream outputStream, Class<?> clazz, List<?> datalist, ExcelTypeEnum excelType, String sheetName) throws IOException {
        EasyExcel.write(outputStream, clazz).excelType(excelType).sheet(sheetName==null ? "sheet1":sheetName).doWrite(datalist);
        outputStream.flush();
    }

    /**
     * @Title: writeExcel
     * @Description: 写入excel到本地路径
     */
    private void writeExcel(File newFile, Class<?> clazz, List<?> datalist, ExcelTypeEnum excelType) {
        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)11);
        headWriteCellStyle.setWriteFont(headWriteFont);

        ExcelWriter excelWriter = null;
        try{
            MonthSheetWriteHandler monthSheetWriteHandler = new MonthSheetWriteHandler();
            monthSheetWriteHandler.setSupplierName("xx供应商");
            monthSheetWriteHandler.setCityCompanyName("xx分公司");
            excelWriter =  EasyExcel.write(newFile, clazz)
                    //设置输出excel版本,不设置默认为xlsx
                    .excelType(ExcelTypeEnum.XLS).head(PilebodycheckMonthDto.class)
                    //设置拦截器或自定义样式
                    .registerWriteHandler(monthSheetWriteHandler)
                    .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle))
                    .registerWriteHandler(new CustomerRowWriteHandler())
                    // .sheet("存量建筑垃圾堆体治理进度月报表")
                    //设置默认样式及写入头信息开始的行数
                    .useDefaultStyle(true).relativeHeadRowIndex(10)
                    //这里的addsumColomn方法是个添加合计的方法,可删除
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet("存量建筑垃圾堆体治理进度月报表").build();
            excelWriter.write(datalist, writeSheet);
        }finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
       // EasyExcel.write(newFile, clazz).excelType(excelType).sheet("sheet1").doWrite(datalist);
    }

    /**
     * @Title: readExcel
     * @Description: 读取excel内容（从输入流）
     */
    private List<?> readExcel(InputStream inputStream, Class<?> clazz, ReadListener<?> listener) {
        List<?> list = null;
        list = EasyExcel.read(inputStream, clazz, listener).sheet().doReadSync();
        return list;
    };
}
