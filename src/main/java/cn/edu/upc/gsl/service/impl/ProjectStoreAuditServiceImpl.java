package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectStoreAuditServiceImpl implements ProjectStoreAuditService {

    @Resource
    ProjectStoreMapper projectStoreMapper;

    /**
     * 新增项目申报
     * @param projectStore
     */
    @Override
    public int addProject(ProjectStore projectStore){
       return projectStoreMapper.addProject(projectStore);
    }
}
