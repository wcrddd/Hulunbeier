package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.model.ProjectStore;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 获取全部项目的初步设计报告
     * @param
     * @return
     */
    @RequestMapping("/getAllpredesignReport")
    @ResponseBody
    public CommonReturnType getAllpredesignReport(){

        return CommonReturnType.create(predesignReportService.getAllpredesignReport());
    }
}
