package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.model.ProjectStore;
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

    @RequestMapping("/insertAppendix")
    @ResponseBody
    public CommonReturnType insertAppendix(@RequestBody PredesignReportAppendix predesignReportAppendix){
        predesignReportService.insertAppendix(predesignReportAppendix);
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
        predesignReportService.updateAppendix(predesignReportAppendix);
        return  CommonReturnType.create("删除成功");
    }
}
