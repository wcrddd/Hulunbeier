package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.ProjectStore;

import java.util.List;

/**
 * @author gsl
 * @date 2020/5/18
 */
public interface ProjectStoreAuditService {


    /**
     * 新增项目
     * @param projectStore
     * @return
     */
    int addProject(ProjectStore projectStore);

    /**
     * 查询项目
     * @param projectName
     * @param buildYear
     * @return
     */
    List<ProjectStore> getProject(String projectName,String buildYear);

    /**
     * 更新项目状态
     * @param record
     */
    void updateState(ProjectStore record);
}
