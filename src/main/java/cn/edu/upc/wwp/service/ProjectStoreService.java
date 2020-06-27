package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectStore;

import java.util.List;

public interface ProjectStoreService {

    public List<ProjectStore> selectProjectStore();
    public List<ProjectStore> searchProjectStore(ProjectStore projectStore);
    public List<ProjectStore> getProjectStoreByUnitId(int unitId);
}
