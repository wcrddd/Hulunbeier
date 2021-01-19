package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    /**
     * 上传可研报告
     * @param studyReport
     * @return
     */
    @RequestMapping("/insertReport")
    @ResponseBody
    public CommonReturnType insertReport(@RequestBody StudyReport studyReport){
        studyReportService.insertReport(studyReport);
        return CommonReturnType.create(null,"新增成功");
    }

    /**
     * 更新可研报告
     * @param studyReport
     * @return
     */
    @RequestMapping("/updateReport2")
    @ResponseBody
    public CommonReturnType updateReport2(@RequestBody StudyReport studyReport){
        studyReportService.updateReport(studyReport);
        return CommonReturnType.create(null,"修改成功");
    }

    /**
     * 审批
     * @param studyReport
     * @return
     */
    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody StudyReport studyReport){
        studyReportService.updateApprove(studyReport);
        return CommonReturnType.create(null,"审批成功");
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

    /**
     * 更新填报内容
     * @param feasibilityResearchReport
     * @return
     */
    @RequestMapping("/updateReport")
    @ResponseBody
    public CommonReturnType updateReport(@RequestBody FeasibilityResearchReport feasibilityResearchReport){
        studyReportService.updateReport(feasibilityResearchReport);
        return CommonReturnType.create(null,"更新成功");
    }

    /**
     * 审核
     * @param feasibilityResearchReport
     * @return
     */
    @RequestMapping("/updateApproveExamine")
    @ResponseBody
    public CommonReturnType updateApproveExamine(@RequestBody FeasibilityResearchReport feasibilityResearchReport){
        studyReportService.updateReport2(feasibilityResearchReport);
        return  CommonReturnType.create("审核成功");
    }

    /**
     * 获取本单位的可研报告填报内容，用于审核
     * @param
     * @return
     */
    @RequestMapping("/getFeasibilityByUnitId")
    @ResponseBody
    public CommonReturnType getFeasibilityByUnitId(HttpSession session,@RequestBody JSONObject jsonObject){
                int unitId= SysUser.getCurrentUserUnitId(session);
//        int unitId=1;
        String projectName = jsonObject.getString("projectName");
        return  CommonReturnType.create(studyReportService.getFeasibilityByUnitId(unitId,projectName));
    }

    /**
     * 根据项目id获取附件
     * @param jsonObject
     * @return
     */
    @RequestMapping("/getAppendixBuProjectId")
    @ResponseBody
    public CommonReturnType getAppendixBuProjectId(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return  CommonReturnType.create(studyReportService.getAppendixBuProjectId(projectId));
    }

    /**
     * 获取所以单位审核通过的可研报告填报内容（即approve=1  ），用于审批（改2020-12-01）
     * @param
     * @return
     */
    @RequestMapping("/getAllApprovedFeasibility")
    @ResponseBody
    public CommonReturnType getAllApprovedFeasibility(@RequestBody StudyReport studyReport){
        return  CommonReturnType.create(studyReportService.getAllApprovedFeasibility(studyReport));
    }

    /**
     * 根据项目id获取特定的可研报告（改2020-12-01）
     * @param projectId
     * @return
     */
    @RequestMapping("/getByProjectId")
    @ResponseBody
    public CommonReturnType getByProjectId(@RequestParam("projectId") int projectId){
        return CommonReturnType.create(studyReportService.getByProjectId(projectId),"获取成功");
    }

}
