package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectPlan;

import java.util.List;

public interface ProjectPlanService {
    public List<ProjectPlan> selectProjectPlan();
    public void insertProjectPlan(ProjectPlan projectPlan);
}
