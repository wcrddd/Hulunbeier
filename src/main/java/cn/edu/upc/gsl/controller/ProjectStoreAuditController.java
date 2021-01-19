package cn.edu.upc.gsl.controller;

import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.ProjectYearPlan;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.ProjectStoreFlagVo;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import cn.edu.upc.manage.vo.ProjectYearPlanVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public CommonReturnType addProject(@RequestBody ProjectStore projectStore,HttpServletRequest httpServletRequest,HttpSession session){
//        int unitId=1;
        projectStoreAuditService.addProject(projectStore, session, httpServletRequest);
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
    public CommonReturnType getProject(@RequestBody ProjectStore projectStore, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String projectName = projectStore.getProjectName();
        String buildYear = projectStore.getBuildYear();
        if(user.getUserType()==1){
            List<ProjectStore> projectStoreList = projectStoreAuditService.getProject(projectName, buildYear);
            if (projectStoreList.size() == 0) {
                return CommonReturnType.create(null, "未查询到符合条件的项目");
            }
            return CommonReturnType.create(projectStoreList);
        }else{
            List<ProjectStore> projectStoreList = projectStoreAuditService.getProject2(projectName, buildYear, SysUser.getCurrentUserUnitId(session));
            if (projectStoreList.size() == 0) {
                return CommonReturnType.create(null, "未查询到符合条件的项目");
            }
            return CommonReturnType.create(projectStoreList);
        }

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
     * 更新项目状态
     * @param projectStore 主要是三个参数
     * id ,approve, opinion
     * 0 未审核，1一级库 ,2二级库
     * @return
     */
    @RequestMapping(value = "/updateStoreFlag")
    @ResponseBody
    public CommonReturnType updateStoreFlag(@RequestBody ProjectStore projectStore){
        projectStoreAuditService.updateStoreFlag(projectStore);
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

    /**
     * 挑选当年计划的项目
     * @param projectYearPlanVo
     * @return
     */
    @RequestMapping(value = "/selectYearPlan")
    @ResponseBody
    public CommonReturnType selectYearPlan(@RequestBody ProjectYearPlanVo projectYearPlanVo){
        projectStoreAuditService.selectYearPlan(projectYearPlanVo);
        return CommonReturnType.create(null,"操作成功");
    }

    /**
     * 挑选当年计划的项目
     * @param projectYearPlan
     * @return
     */
    @RequestMapping(value = "/selectYearPlan2")
    @ResponseBody
    public CommonReturnType selectYearPlan2(@RequestBody ProjectYearPlan projectYearPlan){
        projectStoreAuditService.selectYearPlan2(projectYearPlan);
        return CommonReturnType.create(null,"操作成功");
    }


    /**
     * 删除当年计划的一个项目
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/deleteYearPlanProject")
    @ResponseBody
    public CommonReturnType deleteYearPlanProject(@RequestParam("projectId") int projectId){
        projectStoreAuditService.deleteYearPlanProject(projectId);
        return CommonReturnType.create(null,"操作成功");
    }

    /**
     * 二级单位浏览本单位的全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/getByUnitId")
    @ResponseBody
    public CommonReturnType getByUnitId(@RequestBody ProjectStoreFlagVo projectStoreFlagVo,HttpServletRequest httpServletRequest,HttpSession httpSession){
        String token = httpServletRequest.getHeader("token");
        int unitId = SysUser.getCurrentUserUnitId2(httpSession,token);
//        System.out.println("单位id:" + unitId1);
//        int unitId = 6;
        System.out.println(projectStoreFlagVo.getStoreFlag()+",   "+projectStoreFlagVo.getPlanedFlag());
        return CommonReturnType.create(projectStoreAuditService.getByUnitId(projectStoreFlagVo, unitId));
    }

    /**
     * 审批部门获取全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @return
     */
    @RequestMapping(value = "/getAll")
    @ResponseBody
    public CommonReturnType getAll(@RequestBody ProjectStoreFlagVo projectStoreFlagVo,HttpServletRequest httpServletRequest,HttpSession httpSession){
//        String token = httpServletRequest.getHeader("token");
//        User user = (User) httpSession.getAttribute(token);
        User user = SysUser.getCurrentUser(httpSession,httpServletRequest);
        return CommonReturnType.create(projectStoreAuditService.getAll(projectStoreFlagVo, user));
    }

    /**
     * 获取合同显示界面的项目
     * @param projectStoreFlagVo
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/getByUnitIdConstract")
    @ResponseBody
    public CommonReturnType getByUnitIdConstract(@RequestBody ProjectStoreFlagVo projectStoreFlagVo,HttpServletRequest httpServletRequest,HttpSession httpSession){
        String token = httpServletRequest.getHeader("token");
        int unitId = SysUser.getCurrentUserUnitId2(httpSession,token);
//        int unitId = 6;
        return CommonReturnType.create(projectStoreAuditService.getByUnitIdConstract(projectStoreFlagVo, unitId));
    }

    /**
     * 获取竣工界面显示
     * @param projectStoreFlagVo
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/getByUnitIdFinish")
    @ResponseBody
    public CommonReturnType getByUnitIdFinish(@RequestBody ProjectStoreFlagVo projectStoreFlagVo,HttpServletRequest httpServletRequest,HttpSession httpSession){
        String token = httpServletRequest.getHeader("token");
        int unitId = SysUser.getCurrentUserUnitId2(httpSession,token);
//        int unitId = 6;
        return CommonReturnType.create(projectStoreAuditService.getByUnitIdFinish(projectStoreFlagVo, unitId));
    }

    /**
     * 设置重点项目
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/updateImportant")
    @ResponseBody
    public CommonReturnType updateImportant(@RequestParam("projectId") int projectId){
        projectStoreAuditService.updateImportant(projectId);
        return CommonReturnType.create("设置成功");
    }

    /**
     * 更新项目建议
     * @param projectStore
     * @return
     */
    @RequestMapping(value = "/updateSuggestion")
    @ResponseBody
    public CommonReturnType updateSuggestion(@RequestBody ProjectStore projectStore){
        projectStoreAuditService.updateSuggestion(projectStore);
        return CommonReturnType.create("填写成功");
    }

    /**
     * 获取数量统计
     * @return
     */
    @RequestMapping(value = "/getNumStatistics")
    @ResponseBody
    public CommonReturnType getNumStatistics(){
        return CommonReturnType.create(projectStoreAuditService.getNumStatistics());
    }

    /**
     * 获取带坐标的项目
     * @return
     */
    @RequestMapping(value = "/getProjectLocation")
    @ResponseBody
    public CommonReturnType getProjectLocation(){
        return CommonReturnType.create(projectStoreAuditService.getProjectLocation());
    }

    /**
     * 获取全部单位数量统计
     * @return
     */
    @RequestMapping(value = "/getConstructUnitStatistic")
    @ResponseBody
    public CommonReturnType getConstructUnitStatistic(){
        return CommonReturnType.create(projectStoreAuditService.getConstructUnitStatistic());
    }

    /**
     * 获取带坐标的项目
     * @return
     */
    @RequestMapping(value = "/getProjectLocationClass")
    @ResponseBody
    public CommonReturnType getProjectLocationClass(int flag){
        return CommonReturnType.create(projectStoreAuditService.getProjectLocationClass(flag));
    }

}
