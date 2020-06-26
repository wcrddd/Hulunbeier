package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ProjectStoreVo;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 只返回审核通过的（approve =1）
     * @return
     */
    List<ProjectStore> selectProjectPass();

    ProjectStore selectProjectById(Integer id);

    void update(ProjectStore projectStore);

    /**
     * 区分已计划申报的和未进行计划申报的项目
     * @param httpServletRequest
     * @return
     */
    List<ProjectStoreVo> divideProjectPlan(HttpServletRequest httpServletRequest);
}
