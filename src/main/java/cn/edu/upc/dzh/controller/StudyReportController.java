package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.common.CommonReturnType;
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

    @RequestMapping("/insertStudyReport")
    @ResponseBody
    public CommonReturnType insertStudyReport(@RequestBody StudyReport studyReport){
        studyReportService.insertStudyReport(studyReport);
        return CommonReturnType.create(null,"新增完成");
    }

}
