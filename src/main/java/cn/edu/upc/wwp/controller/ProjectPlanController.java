package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
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
    public CommonReturnType select(@RequestBody JSONObject jsonObject){
        String projectName = jsonObject.getString("projectName");
        List<ProjectPlanParam> list1=  projectPlanService.selectProjectPlan(projectName);
        return  CommonReturnType.create(list1);
    }


    @RequestMapping("/updateProjectPlan")//更新
    @ResponseBody
    public CommonReturnType update(@RequestBody ProjectPlan recordUp){

        projectPlanService.updateProjectPlan(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/updateApproveExamine")//更新审核审批
    @ResponseBody
    public CommonReturnType updateApproveExamine(@RequestBody ProjectPlan recordUp){

        projectPlanService.updateApproveExamine(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/getProjectPlanByUnitId")//查看本单位的

    @ResponseBody
    public CommonReturnType getProjectPlanByUnitId(HttpSession session,@RequestBody JSONObject jsonObject){
//        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        String projectName = jsonObject.getString("projectName");
        List<ProjectPlanParam> list1=  projectPlanService.getProjectPlanByUnitId(unitId,projectName);
        return  CommonReturnType.create(list1);
    }

    /**
     * 获取本单位可以填报可研报告的项目
     * @param session
     * @return
     */
    @RequestMapping("/getApprovedProjectByUnitId")
    @ResponseBody
    public CommonReturnType getApprovedProjectByUnitId(HttpSession session,@RequestBody JSONObject jsonObject){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        String projectName = jsonObject.getString("projectName");
        return  CommonReturnType.create(projectPlanService.getApprovedProjectByUnitId(unitId,projectName),"查询成功");
    }

    /**
     * 获取本单位可以填报初步设计的项目
     * @param session
     * @return
     */
    @RequestMapping("/getCanDesignByUnitId")
    @ResponseBody
    public CommonReturnType getCanDesignByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectPlanService.getCanDesignByUnitId(unitId),"查询成功");
    }

    /**
     * 获取本单位已经填报的初步设计，进行审核
     * @param session
     * @return
     */
    @RequestMapping("/getDesignByUnitId")
    @ResponseBody
    public CommonReturnType getDesignByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectPlanService.getDesignByUnitId(unitId),"查询成功");
    }

    /**
     * 获取全部单位已经审核通过的初步设计，进行审批
     * @param
     * @return
     */
    @RequestMapping("/getAllApprovedDesign")
    @ResponseBody
    public CommonReturnType getAllApprovedDesign(){
        return  CommonReturnType.create(projectPlanService.getAllApprovedDesign(),"查询成功");
    }


}
