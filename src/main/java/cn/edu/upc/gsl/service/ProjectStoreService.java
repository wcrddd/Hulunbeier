package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.ProjectStore;

/**
 * @author gsl
 * @date 2020/5/18
 */
public interface ProjectStoreService {


    /**
     * 新增项目
     * @param projectStore
     * @return
     */
    int addProject(ProjectStore projectStore);
}
