package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.wwp.service.ProjectStoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("projectStoreService")
public class ProjectStoreServiceImpl implements ProjectStoreService {
    @Resource
    ProjectStoreMapper projectStoreMapper;
    @Override
    public List<ProjectStore> selectProjectStore() {
        return projectStoreMapper.selectProjectStore();
    }

    @Override
    public List<ProjectStore> searchProjectStore(ProjectStore projectStore) {
        return projectStoreMapper.searchProjectStore(projectStore);
    }

    @Override
    public List<ProjectStore> getProjectStoreByUnitId(int unitId){
        return projectStoreMapper.selectPassProjectByUnitId2(unitId);
    }
}
