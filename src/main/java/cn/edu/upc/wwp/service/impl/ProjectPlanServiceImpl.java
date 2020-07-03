package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ProjectPlanMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectPlan;

import cn.edu.upc.manage.vo.ProjectPlanDesign;
import cn.edu.upc.manage.vo.ProjectPlanFlag;
import cn.edu.upc.wwp.controller.param.ProjectPlanParam;
import cn.edu.upc.wwp.service.ProjectPlanService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("projectPlanService")
public class ProjectPlanServiceImpl implements ProjectPlanService {

    @Resource
    ProjectPlanMapper projectPlanMapper;


    @Override
    public List<ProjectPlanParam> selectProjectPlan() {
        return projectPlanMapper.selectProjectPlan();
    }

    @Override
    public void insertProjectPlan(ProjectPlan projectPlan) {
        projectPlan.setOperator("test");
        projectPlanMapper.insertSelective(projectPlan);
    }

    @Override
    public void updateProjectPlan(ProjectPlan recordUp) {
        recordUp.setOperator("test");
        projectPlanMapper.update(recordUp);
    }

    @Override
    public List<ProjectPlanParam> getProjectPlanByUnitId(int unitId){
        return projectPlanMapper.getProjectPlanByUnitId(unitId);
    }

    @Transactional
    @Override
    public void updateApproveExamine(ProjectPlan recordUp){
        projectPlanMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public List<ProjectPlanFlag> getApprovedProjectByUnitId(int unitId){
        return projectPlanMapper.getApprovedProjectByUnitId(unitId);
    }

    @Override
    public List<ProjectPlanFlag> getCanDesignByUnitId(int unitId){
        return projectPlanMapper.getCanDesignByUnitId(unitId);
    }

    @Override
    public List<ProjectPlanDesign> getDesignByUnitId(int unitId){
        return projectPlanMapper.getDesignByUnitId(unitId);
    }

    @Override
    public List<ProjectPlanDesign> getAllApprovedDesign(){
        return projectPlanMapper.getAllApprovedDesign();
    }
}
