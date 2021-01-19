package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.StartReportService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.StartReport;
import cn.edu.upc.manage.model.TenderInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 董志涵 2020-11-29
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/startReport")
public class StartReportController {
    @Autowired
    private StartReportService startReportService;

    /**
     * 新增
     * @param startReport
     * @return
     */
    @RequestMapping(value = "/addReport")
    @ResponseBody
    public CommonReturnType addReport(@RequestBody StartReport startReport){
        startReportService.addReport(startReport);
        return CommonReturnType.create(null,"开工增加成功");
    }

    /**
     * 审批
     * @param startReport
     * @return
     */
    @RequestMapping(value = "/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody StartReport startReport){
        startReportService.updateApprove(startReport);
        return CommonReturnType.create(null,"审批成功");
    }

    /**
     * 修改
     * @param startReport
     * @return
     */
    @RequestMapping(value = "/updateReport")
    @ResponseBody
    public CommonReturnType updateReport(@RequestBody StartReport startReport){
        startReportService.updateReport(startReport);
        return CommonReturnType.create(null,"修改成功");
    }

    /**
     * 获取全部
     * @return
     */
    @RequestMapping(value = "/getAllReport")
    @ResponseBody
    public CommonReturnType getAllReport(){
        List<StartReport> startReportList = startReportService.getAllReport();
        return CommonReturnType.create(startReportList);
    }

    /**
     * 根据项目id获取开工报告
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/getByProjectId")
    @ResponseBody
    public CommonReturnType getByProjectId(@RequestParam("projectId")int projectId){
        StartReport startReport = startReportService.getByProjectId(projectId);
        return CommonReturnType.create(startReport);
    }


}
