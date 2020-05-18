package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ProjectPlanMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectPlan;

import cn.edu.upc.wwp.service.ProjectPlanService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("projectPlanService")
public class ProjectPlanServiceImpl implements ProjectPlanService {

    @Resource
    ProjectPlanMapper projectPlanMapper;


    @Override
    public void insertProjectPlan(ProjectPlan projectPlan) {
        projectPlan.setOperator("test");
        projectPlanMapper.insertSelective(projectPlan);
    }
}
