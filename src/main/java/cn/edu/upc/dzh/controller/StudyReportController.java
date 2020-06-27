package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/studyReport",method={RequestMethod.POST, RequestMethod.GET})
public class StudyReportController {
    @Autowired
    private StudyReportService studyReportService;

    @RequestMapping("/insertAppendix")
    @ResponseBody
    public CommonReturnType insertAppendix(@RequestBody StudyReport studyReport){
        studyReportService.insertAppendix(studyReport);
        return CommonReturnType.create(null,"新增成功");
    }

    @RequestMapping("/insertReport")
    @ResponseBody
    public CommonReturnType insertReport(@RequestBody FeasibilityResearchReport feasibilityResearchReport){
        studyReportService.insertReport(feasibilityResearchReport);
        return CommonReturnType.create(null,"新增成功");
    }

//    @RequestMapping("/deleteReport")
//    @ResponseBody
//    public CommonReturnType deleteReport(@RequestBody JSONObject jsonObject ){
//        int
//        studyReportService.insertReport(feasibilityResearchReport);
//        return CommonReturnType.create(null,"新增成功");
//    }
//
//    @RequestMapping("/getReportByProjectId")
//    @ResponseBody
//    public CommonReturnType getReportByProjectId(@RequestBody JSONObject jsonObject){
//        int projectId=jsonObject.getInteger("projectId");
//        studyReportService.insertReport(feasibilityResearchReport);
//        return CommonReturnType.create(null,"新增成功");
//    }

}
