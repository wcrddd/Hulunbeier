package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectStore;

import java.util.List;

public interface ProjectStoreService {

    public List<ProjectStore> selectProjectStore(int unitId,String projectName);
    public List<ProjectStore> searchProjectStore(ProjectStore projectStore);
    public List<ProjectStore> getProjectStoreByUnitId(int unitId);
    public List<ProjectStore> getCanTenderByUnitId(int unitId);
    public List<ProjectStore> getCanContractByUnitId(int unitId);
    public List<ProjectStore> getCanProgressByUnitId(int unitId);
}
