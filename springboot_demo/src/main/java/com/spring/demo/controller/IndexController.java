package com.spring.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.spring.demo.common.ReturnT;
import com.spring.demo.pojo.MonthSheetWriteHandler;
import com.spring.demo.pojo.PilebodycheckMonthDto;
import com.spring.demo.util.DateUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: cdc
 * @Date: 2020/6/24 15:31
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {

        Map<String, Object> dashboardMap = new HashMap<>();//xxlJobService.dashboardInfo();
        dashboardMap.put("jobInfoCount", 1);
        dashboardMap.put("jobLogCount", 1);
        dashboardMap.put("jobLogSuccessCount", 1);
        dashboardMap.put("executorCount", 1);
        model.addAllAttributes(dashboardMap);

        return "index";
    }

    @RequestMapping("/chartInfo")
    @ResponseBody
    public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {

        // process
        List<String> triggerDayList = new ArrayList<String>();
        List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
        List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
        List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
        int triggerCountRunningTotal = 0;
        int triggerCountSucTotal = 0;
        int triggerCountFailTotal = 0;

/*        List<XxlJobLogReport> logReportList = xxlJobLogReportDao.queryLogReport(startDate, endDate);

        if (logReportList!=null && logReportList.size()>0) {
            for (XxlJobLogReport item: logReportList) {
                String day = DateUtil.formatDate(item.getTriggerDay());
                int triggerDayCountRunning = item.getRunningCount();
                int triggerDayCountSuc = item.getSucCount();
                int triggerDayCountFail = item.getFailCount();

                triggerDayList.add(day);
                triggerDayCountRunningList.add(triggerDayCountRunning);
                triggerDayCountSucList.add(triggerDayCountSuc);
                triggerDayCountFailList.add(triggerDayCountFail);

                triggerCountRunningTotal += triggerDayCountRunning;
                triggerCountSucTotal += triggerDayCountSuc;
                triggerCountFailTotal += triggerDayCountFail;
            }
        } else {
            for (int i = -6; i <= 0; i++) {
                triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
                triggerDayCountRunningList.add(0);
                triggerDayCountSucList.add(0);
                triggerDayCountFailList.add(0);
            }
        }*/

        for (int i = -6; i <= 0; i++) {
            triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
            triggerDayCountRunningList.add(0);
            triggerDayCountSucList.add(0);
            triggerDayCountFailList.add(0);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("triggerDayList", triggerDayList);
        result.put("triggerDayCountRunningList", triggerDayCountRunningList);
        result.put("triggerDayCountSucList", triggerDayCountSucList);
        result.put("triggerDayCountFailList", triggerDayCountFailList);

        result.put("triggerCountRunningTotal", triggerCountRunningTotal);
        result.put("triggerCountSucTotal", triggerCountSucTotal);
        result.put("triggerCountFailTotal", triggerCountFailTotal);

        return new ReturnT<Map<String, Object>>(result);

    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        /*if (loginService.ifLogin(request, response) != null) {
            return "redirect:/";
        }*/
        return "login";
    }

    @RequestMapping(value="login", method= RequestMethod.POST)
    @ResponseBody
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
        boolean ifRem = (ifRemember!=null && ifRemember.trim().length()>0 && "on".equals(ifRemember))?true:false;
        return ReturnT.SUCCESS;//loginService.login(request, response, userName, password, ifRem);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/index2")
    public void easyExcelTest(HttpServletResponse response) throws IOException {
        List<PilebodycheckMonthDto> pilebodysList = new ArrayList(3);//pilebodycheckService.pilebodystatisticsmonth(sysDepartDto, month);
        pilebodysList.add(new PilebodycheckMonthDto());
        pilebodysList.add(new PilebodycheckMonthDto());
        pilebodysList.add(new PilebodycheckMonthDto());
        //设置序号
        pilebodysList.get(0).setOrderNum("至上月末");
        pilebodysList.get(1).setOrderNum("本月合计");
        pilebodysList.get(2).setOrderNum("本年累计");

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("企业对账函-函证账户余额及发票", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
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
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
//头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
       // headWriteCellStyle.setWrapped(false);

        //excel如需下载到本地,只需要将response.getOutputStream()换成File即可(注释掉以上response代码)
        EasyExcel.write(response.getOutputStream(), PilebodycheckMonthDto.class)
                //设置输出excel版本,不设置默认为xlsx
                .excelType(ExcelTypeEnum.XLS).head(PilebodycheckMonthDto.class)
                //设置拦截器或自定义样式
                .registerWriteHandler(new MonthSheetWriteHandler())
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle))
                .sheet("存量建筑垃圾堆体治理进度月报表")
                //设置默认样式及写入头信息开始的行数
                .useDefaultStyle(true).relativeHeadRowIndex(9)
                //这里的addsumColomn方法是个添加合计的方法,可删除
                .doWrite(pilebodysList);
       // return new WebApiResponse(200, "生成excel文件成功", null);
    }
}
