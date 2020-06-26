package cn.edu.upc.wwp.controller;

import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Post;
import cn.edu.upc.manage.model.ProjectPlan;
import cn.edu.upc.wwp.controller.param.ProjectPlanParam;
import cn.edu.upc.wwp.service.ProjectPlanService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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


    @RequestMapping("/selectProjectPlan")//查看审核通过的

    @ResponseBody
    public CommonReturnType select(){
        List<ProjectPlanParam> list1=  projectPlanService.selectProjectPlan();
        return  CommonReturnType.create(list1);
    }


    @RequestMapping("/updateProjectPlan")//更新

    @ResponseBody
    public CommonReturnType update(@RequestBody ProjectPlan recordUp){

        projectPlanService.updateProjectPlan(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/getProjectPlanByUnitId")//查看本单位的

    @ResponseBody
    public CommonReturnType getProjectPlanByUnitId(HttpSession session){
//        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=2;
        List<ProjectPlanParam> list1=  projectPlanService.getProjectPlanByUnitId(unitId);
        return  CommonReturnType.create(list1);
    }


}
