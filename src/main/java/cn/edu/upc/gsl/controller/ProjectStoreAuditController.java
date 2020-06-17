package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gsl
 * @date 2020/5/16
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/project",method = {RequestMethod.GET,RequestMethod.POST})
public class ProjectStoreAuditController {
    @Autowired
    ProjectStoreAuditService projectStoreAuditService;

    /**
     * 项目申报（建设单位）
     * @param projectStore 项目储备库
     * @return
     */
    @RequestMapping(value = "/addProject")
    @ResponseBody
    public CommonReturnType addProject(@RequestBody ProjectStore projectStore){
        int unitId=1;
        projectStore.setConstruUnitId(unitId);
        projectStoreAuditService.addProject(projectStore);
        return CommonReturnType.create(null,"项目申报成功，等待审批");
    }

    /**
     * 查询项目
     * @param projectStore
     * @return
     */

    @RequestMapping(value = "/getProject")
    @ResponseBody
    public CommonReturnType getProject(@RequestBody ProjectStore projectStore) {
        String projectName = projectStore.getProjectName();
        String buildYear = projectStore.getBuildYear();
        List<ProjectStore> projectStoreList = projectStoreAuditService.getProject(projectName, buildYear);
        if (projectStoreList.size() == 0) {
            return CommonReturnType.create(null, "未查询到符合条件的项目");
        }
        return CommonReturnType.create(projectStoreList);
    }

    /**
     * 更新项目状态
     * @param projectStore 主要是三个参数
     * id ,approve, opinion
     * 0 未审核，1审核通过 ,2不通过
     * @return
     */
    @RequestMapping(value = "/updateState")
    @ResponseBody
    public CommonReturnType updateState(@RequestBody ProjectStore projectStore){
        projectStoreAuditService.updateState(projectStore);
        return CommonReturnType.create(null,"操作成功");
    }

    /**
     * 获取审批通过和未通过的项目
     * @return
     */
    @RequestMapping(value = "/passAndNo")
    @ResponseBody
    public CommonReturnType selectProjectPassAndNo(){
        List<ProjectStore> projectStoreList = projectStoreAuditService.selectProjectPassAndNo();
        return CommonReturnType.create(projectStoreList,"查询成功");
    }

    @RequestMapping(value = "/selectById")
    @ResponseBody
    public CommonReturnType selectProjectById(@RequestBody JSONObject jsonObject){
        int id=jsonObject.getInteger("id");
       ProjectStore project = projectStoreAuditService.selectProjectById(id);
       if (project != null) {
           return CommonReturnType.create(project, "查询成功");
       }
       else {
           return CommonReturnType.create(null,"未查询到");
       }
    }

}
