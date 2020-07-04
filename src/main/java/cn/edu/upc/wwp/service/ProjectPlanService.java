package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectPlan;
import cn.edu.upc.manage.vo.ProjectPlanDesign;
import cn.edu.upc.manage.vo.ProjectPlanFlag;
import cn.edu.upc.wwp.controller.param.ProjectPlanParam;

import java.util.List;

public interface ProjectPlanService {
    public List<ProjectPlanParam> selectProjectPlan(String projectName);
    public void insertProjectPlan(ProjectPlan projectPlan);
    public void updateProjectPlan(ProjectPlan recordUp);
    public List<ProjectPlanParam> getProjectPlanByUnitId(int unitId,String projectName);
    public void updateApproveExamine(ProjectPlan recordUp);
    public List<ProjectPlanFlag> getApprovedProjectByUnitId(int unitId,String projectName);
    public List<ProjectPlanFlag> getCanDesignByUnitId(int unitId);
    public List<ProjectPlanDesign> getDesignByUnitId(int unitId);
    public List<ProjectPlanDesign> getAllApprovedDesign();
}
