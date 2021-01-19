package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.mo.PredesignReportMo;
import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.PredesignReportVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/predesignReport",method = {RequestMethod.POST,RequestMethod.GET})
public class predesignReportController {
    @Autowired
    private PredesignReportService predesignReportService;

//    @RequestMapping("/insertAppendix")
//    @ResponseBody
//    public CommonReturnType insertAppendix(@RequestBody PredesignReportAppendix predesignReportAppendix){
//        predesignReportService.insertAppendix(predesignReportAppendix);
//        return  CommonReturnType.create("新增成功");
//    }
@RequestMapping("/insertAppendix")
@ResponseBody
public CommonReturnType insertAppendix(@RequestBody JSONObject jsonObject){
    int projectId=jsonObject.getInteger("projectId");
    JSONArray appendixNameList=jsonObject.getJSONArray("appendixNameList");
    JSONArray appendixList=jsonObject.getJSONArray("appendixList");
    PredesignReportAppendix predesignReportAppendix=new PredesignReportAppendix();
    for(int i=0;i<appendixList.size();i++){
        System.out.println(appendixNameList.getString(i)+"  "+appendixList.getString(i));
        predesignReportAppendix.setProjectId(projectId);
        predesignReportAppendix.setAppendixName(appendixNameList.getString(i));
        predesignReportAppendix.setAppendix(appendixList.getString(i));
        predesignReportService.insertAppendix(predesignReportAppendix);
    }
    return  CommonReturnType.create("新增成功");
}

    @RequestMapping("/getAppendixByProjectId")//根据项目id获取设计的附件
    @ResponseBody
    public CommonReturnType getAppendixByProjectId(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return CommonReturnType.create(predesignReportService.getAppendixByProjectId(projectId));
    }

    @RequestMapping("/updateAppendix")//更新
    @ResponseBody
    public CommonReturnType updateAppendix(@RequestBody PredesignReportAppendix predesignReportAppendix){
        predesignReportService.updateAppendix(predesignReportAppendix);
        return  CommonReturnType.create("修改成功");
    }

    @RequestMapping("/deleteAppendix")//删除设计的附件
    @ResponseBody
    public CommonReturnType deleteAppendix(@RequestBody PredesignReportAppendix predesignReportAppendix){
        predesignReportAppendix.setDelFlag(predesignReportAppendix.getId());
        predesignReportService.updateAppendix(predesignReportAppendix);
        return  CommonReturnType.create("删除成功");
    }

    @RequestMapping("/updateApproveExamine")//审核
    @ResponseBody
    public CommonReturnType updateApproveExamine(@RequestBody PredesignReport predesignReport){
        predesignReportService.updateApproveExamine(predesignReport);
        return  CommonReturnType.create("审核成功");
    }

    /*****
     * 获取全部项目的初步设计报告用于审批
     * @param
     * @return
     */
    @RequestMapping("/getAllpredesignReport")
    @ResponseBody
    public CommonReturnType getAllpredesignReport(){

        return CommonReturnType.create(predesignReportService.getAllpredesignReport());
    }

    /**
     * 获取本单位的初步设计报告用于审核
     * @param session
     * @return
     */
    @RequestMapping("/getCanApproveByUnitId")
    @ResponseBody
    public CommonReturnType getCanApproveByUnitId(HttpSession session){
                int unitId= SysUser.getCurrentUserUnitId(session);
//        int unitId=1;
        return CommonReturnType.create(predesignReportService.getCanApproveByUnitId(unitId));
    }

    @RequestMapping("/selectCanApprove")
    @ResponseBody
    public CommonReturnType selectCanApprove(HttpSession session,@RequestBody JSONObject jsonObject){
        String name=jsonObject.getString("projectName");
                int unitId= SysUser.getCurrentUserUnitId(session);
//        int unitId=1;
        return CommonReturnType.create(predesignReportService.selectCanApprove(unitId,name));
    }

    @RequestMapping("/selectAllpredesignReport")
    @ResponseBody
    public CommonReturnType selectAllpredesignReport(@RequestBody JSONObject jsonObject){
        String name=jsonObject.getString("projectName");
        return CommonReturnType.create(predesignReportService.selectAllpredesignReport(name));
    }

    /**
     * 新增初步设计
     * @param predesignReportVo
     * @return
     */
    @RequestMapping("/insertReport")
    @ResponseBody
    public CommonReturnType insertReport(@RequestBody PredesignReportVo predesignReportVo){
        predesignReportService.insertReport(predesignReportVo);
        return CommonReturnType.create(null);
    }

    /**
     * 修改初步设计
     * @param predesignReportVo
     * @return
     */
    @RequestMapping("/updateReport")
    @ResponseBody
    public CommonReturnType updateReport(@RequestBody PredesignReportVo predesignReportVo){
        predesignReportService.updateReport(predesignReportVo);
        return CommonReturnType.create(null);
    }

    /**
     * 删除标段
     * @param sectionId
     * @return
     */
    @RequestMapping("/deleteSection")
    @ResponseBody
    public CommonReturnType deleteSection(@RequestParam("sectionId") int sectionId){
        predesignReportService.deleteSection(sectionId);
        return CommonReturnType.create(null);
    }

    /**
     * 根据项目id获取某个具体的初步设计
     * @param projectId
     * @return
     */
    @RequestMapping("/getByProjectId")
    @ResponseBody
    public CommonReturnType getByProjectId(@RequestParam("projectId") int projectId ){
        PredesignReportMo predesignReportMo = predesignReportService.getByProjectId(projectId);
        return CommonReturnType.create(predesignReportMo);
    }

    /**
     * 新增备案
     * @param predesignReport
     * @return
     */
    @RequestMapping("/insertKeepRecord")
    @ResponseBody
    public CommonReturnType insertKeepRecord(@RequestBody PredesignReport predesignReport){
        predesignReportService.insertKeepRecord(predesignReport);
        return CommonReturnType.create(null);
    }

    /**
     * 审批
     * @param predesignReport
     * @return
     */
    @RequestMapping("/updateKeepApprove")
    @ResponseBody
    public CommonReturnType updateKeepApprove(@RequestBody PredesignReport predesignReport){
        predesignReportService.updateKeepApprove(predesignReport);
        return CommonReturnType.create(null);
    }

    /**
     * 根据项目id获取某个项目的备案
     * @param projectId
     * @return
     */
    @RequestMapping("/getKeepByProjectId")
    @ResponseBody
    public CommonReturnType getKeepByProjectId(@RequestParam("projectId") int projectId){
        return CommonReturnType.create(predesignReportService.getKeepByProjectId(projectId));
    }


}
