package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ProjectStoreApprove;
import cn.edu.upc.wwp.service.ProjectStoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("projectStoreService")
public class ProjectStoreServiceImpl implements ProjectStoreService {
    @Resource
    ProjectStoreMapper projectStoreMapper;
    @Override
    public List<ProjectStore> selectProjectStore(int unitId, String projectName) {
        return projectStoreMapper.selectProjectStore(unitId,projectName);
    }

    @Override
    public List<ProjectStore> searchProjectStore(ProjectStore projectStore) {
        return projectStoreMapper.searchProjectStore(projectStore);
    }

    @Override
    public List<ProjectStore> getProjectStoreByUnitId(int unitId){
        return projectStoreMapper.selectPassProjectByUnitId2(unitId);
    }

    @Override
    public List<ProjectStore> getCanTenderByUnitId(int unitId){
        return projectStoreMapper.getCanTenderByUnitId(unitId);
    }

    @Override
    public List<ProjectStore> getCanContractByUnitId(int unitId){
        return projectStoreMapper.getCanContractByUnitId(unitId);
    }

    @Override
    public List<ProjectStore> getCanProgressByUnitId(int unitId){
        return projectStoreMapper.getCanProgressByUnitId(unitId);
    }

    @Override
    public List<ProjectStoreApprove> getCanDesignByUnitId(int unitId){
        return projectStoreMapper.getCanDesignByUnitId(unitId);
    }

    @Override
    public List<ProjectStoreApprove> selectCanDesign(int unitId,String projectName){
        return projectStoreMapper.selectCanDesign(unitId,projectName);
    }

}
