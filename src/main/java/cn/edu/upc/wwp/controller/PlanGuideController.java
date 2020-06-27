package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.PlanGuide;
import cn.edu.upc.wwp.service.PlanGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/planGuide",method={RequestMethod.POST, RequestMethod.GET})

public class PlanGuideController {

    @Autowired
    public PlanGuideService planGuideService;



    @RequestMapping("/selectPlanGuide")//（1）查看所有计划指南

    @ResponseBody
    public CommonReturnType select(){


        List<PlanGuide> list1= planGuideService.selectPlanGuide();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/addPlanGuide")

    @ResponseBody
    public CommonReturnType insert(@RequestBody PlanGuide planGuide){
        planGuideService.insertProjectGuide(planGuide);
        return  CommonReturnType.create(null);
    }

    @RequestMapping("/updatePlanGuide")//（2）修改计划指南

    @ResponseBody
    public CommonReturnType update(@RequestBody PlanGuide recordUp){

        planGuideService.updatePlanGuide(recordUp);

        return  CommonReturnType.create(null);
    }

}
