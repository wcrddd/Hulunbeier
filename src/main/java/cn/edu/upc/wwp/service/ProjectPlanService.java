package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectPlan;
import cn.edu.upc.wwp.controller.param.ProjectPlanParam;

import java.util.List;

public interface ProjectPlanService {
    public List<ProjectPlanParam> selectProjectPlan();
    public void insertProjectPlan(ProjectPlan projectPlan);
    public void updateProjectPlan(ProjectPlan recordUp);
    public List<ProjectPlanParam> getProjectPlanByUnitId(int unitId);
}
