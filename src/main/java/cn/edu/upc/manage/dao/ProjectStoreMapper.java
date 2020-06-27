package cn.edu.upc.manage.dao;

import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectStoreMapper {

    List<ProjectStore> selectAllProject();

    List<ProjectStore> selectByProjectNameAndBuildYear(@Param("projectName") String projectName, @Param("buildYear") String buildYear);

    List<ProjectStore> selectByProjectName(String projectName);

    List<ProjectStore> selectByBuildYear(String buildYear);

    /**
     * 新增项目申报
     *
     * @param projectStore
     * @return
     */
    int addProject(ProjectStore projectStore);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    int insert(ProjectStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    int insertSelective(ProjectStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    ProjectStore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_store
     *
     * @mbg.generated Sat May 09 21:34:45 CST 2020
     */
    int updateByPrimaryKey(ProjectStore record);

    List<ProjectStore> selectProjectStore();

    List<ProjectStore> searchProjectStore(ProjectStore projectStore);

    /**
     * 返回通过的项目
     * @return
     */
    List<ProjectStore> selectProjectPass();

    /**
     * 返回已经申报的项目
     * @param departmentUnitId
     * @return
     */
    List<ProjectStoreVo> selectProjectPlaned(@Param("departmentUnitId") Integer departmentUnitId);

    /**
     * 根据建设单位id获取通过审核的项目
     * @param departmentUnitId
     * @return
     */
    List<ProjectStoreVo> selectPassProjectByUnitId(@Param("departmentUnitId") Integer departmentUnitId);

    List<ProjectStore> selectPassProjectByUnitId2(@Param("departmentUnitId") Integer departmentUnitId);
    /**
     * 更新计划申报项目的标志位
     * @param id
     * @param planedFlag
     */
    void updatePlanedFlag(@Param("id") Integer id, @Param("planedFlag") Integer planedFlag);
}