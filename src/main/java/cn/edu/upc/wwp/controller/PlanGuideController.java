package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.PlanGuide;
import cn.edu.upc.wwp.service.PlanGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/planGuide",method={RequestMethod.POST, RequestMethod.GET})

public class PlanGuideController {

    @Autowired
    public PlanGuideService planGuideService;

    @RequestMapping("/addPlanGuide")

    @ResponseBody
    public CommonReturnType insert(@RequestBody PlanGuide planGuide){
        planGuideService.insertProjectGuide(planGuide);
        return  CommonReturnType.create(null);
    }

}
