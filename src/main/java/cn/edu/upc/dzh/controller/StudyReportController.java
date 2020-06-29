package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.model.StudyReport;
import com.alibaba.fastjson.JSONArray;
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

    @RequestMapping("/insertAppendix")//上传附件
    @ResponseBody
    public CommonReturnType insertAppendix(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        JSONArray appendixNameList=jsonObject.getJSONArray("appendixNameList");
        JSONArray appendixList=jsonObject.getJSONArray("appendixList");
        StudyReport studyReport=new StudyReport();
        for(int i=0;i<appendixList.size();i++){
            System.out.println(appendixNameList.getString(i)+"  "+appendixList.getString(i));
            studyReport.setProjectId(projectId);
            studyReport.setAppendixName(appendixNameList.getString(i));
            studyReport.setAppendix(appendixList.getString(i));
            studyReportService.insertAppendix(studyReport);
        }
        return CommonReturnType.create(null,"新增成功");
    }

    @RequestMapping("/insertReport")//上传填报内容
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
    @RequestMapping("/getReportByProjectId")
    @ResponseBody
    public CommonReturnType getReportByProjectId(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return CommonReturnType.create(studyReportService.getReportByProjectId(projectId));
    }

    @RequestMapping("/deleteAppendix")//删除附件
    @ResponseBody
    public CommonReturnType deleteAppendix(@RequestBody StudyReport studyReport){
//        int id=jsonObject.getInteger("id");
        studyReportService.deleteAppendix(studyReport);
        return CommonReturnType.create(null,"删除成功");
    }

    @RequestMapping("/updateReport")//更新填报内容
    @ResponseBody
    public CommonReturnType updateReport(@RequestBody FeasibilityResearchReport feasibilityResearchReport){
        studyReportService.updateReport(feasibilityResearchReport);
        return CommonReturnType.create(null,"更新成功");
    }

}
