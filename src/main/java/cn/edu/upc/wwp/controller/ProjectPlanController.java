package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectPlan;
import cn.edu.upc.wwp.service.ProjectPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Controller
@RequestMapping(value="/projectPlan",method={RequestMethod.POST, RequestMethod.GET})

public class ProjectPlanController {
    @Autowired
    public ProjectPlanService projectPlanService;

    @RequestMapping("/insertProjectPlan")

    @ResponseBody
    public CommonReturnType insert(@RequestBody ProjectPlan projectPlan){
        projectPlanService.insertProjectPlan(projectPlan);
        return  CommonReturnType.create(null);
    }
}
