package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/update")
    @ResponseBody
    public CommonReturnType update(@RequestBody ProjectStore projectStore){
        projectStoreAuditService.update(projectStore);
        return CommonReturnType.create(null,"更新完成");
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
     * 只获取审批通过的项目
     * @return
     */
    @RequestMapping(value = "/passed")
    @ResponseBody
    public CommonReturnType selectProjectPass(){
        List<ProjectStore> projectStoreList = projectStoreAuditService.selectProjectPass();
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

    /**
     *
     * 获取项目库中 本建设单位的（未来通过session获取），且审核通过的项目
     * 然后，拿着这些项目id在project_plan查是否存在，存在的用标志符分为一类
     * 不存在的用标志符分为一类。然后返回给前端 项目库里的列表以及标志符
     */
    /**
     * 此接口暂时不用
     *
     * 通过标志位区分开可以申报过的和没有申报的
     * @return 返回这些带标志位的项目信息列表
     */
    @RequestMapping(value = "/divide/plan")
    @ResponseBody
    public CommonReturnType dividePlan(HttpServletRequest httpServletRequest){
        try {
            List<ProjectStoreVo> projectStoreVoList = projectStoreAuditService.divideProjectPlan(httpServletRequest);
            return CommonReturnType.create(projectStoreVoList,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonReturnType.create(null,"服务错误");
        }
    }

    /**
     * 申报计划项目时，设置计划计划申报的标志位
     * 0 代表未申报的项目(数据库中默认为0)
     * 其它数字由 前端设定
     * @return
     */
    @RequestMapping(value = "/set/planedFlag")
    @ResponseBody
    public CommonReturnType setPlanedFlag(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInteger("id");
        Integer planedFlag = jsonObject.getInteger("planedFlag");
        projectStoreAuditService.setPlanedFlag(id,planedFlag);
        return CommonReturnType.create(null,"操作成功");
    }



}
