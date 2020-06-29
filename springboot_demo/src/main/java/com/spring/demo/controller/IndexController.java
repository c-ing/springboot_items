package com.spring.demo.controller;

import com.spring.demo.common.ReturnT;
import com.spring.demo.util.DateUtil;
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
}
