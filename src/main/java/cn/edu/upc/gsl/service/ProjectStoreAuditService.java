package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.mo.ConstructUnitStatisticMo;
import cn.edu.upc.manage.mo.NumStatisticsMo;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.ProjectYearPlan;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.ProjectStoreFlagVo;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import cn.edu.upc.manage.vo.ProjectYearPlanVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    int addProject(ProjectStore projectStore, HttpSession session, HttpServletRequest httpServletRequest);

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

    /**
     * 设置计划申报的标志位
     * @param id
     * @param planedFlag
     */
    void setPlanedFlag(Integer id,Integer planedFlag);

    /**
     * 查询本单位项目
     * @param projectName
     * @param buildYear
     * @return
     */
    List<ProjectStore> getProject2(String projectName, String buildYear,int unitId);

    /**
     * 更新项目状态
     * @param projectStore 主要是三个参数
     * id ,approve, opinion
     * 0 未审核，1一级库 ,2二级库
     * @return
     */
    void updateStoreFlag( ProjectStore projectStore);

    /**
     * 挑选当年计划的项目
     * @param projectYearPlanVo
     */
    void selectYearPlan(ProjectYearPlanVo projectYearPlanVo);

    /**
     * 挑选当年计划的项目
     * @param projectYearPlan
     */
    void selectYearPlan2(ProjectYearPlan projectYearPlan);


    /**
     * 删除当年计划一个项目
     * @param projectId
     */
    void deleteYearPlanProject(int projectId);

    /**
     * 二级单位浏览本单位的全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    List<ProjectStore> getByUnitId(ProjectStoreFlagVo projectStoreFlagVo, int unitId);

    /**
     * 二级单位浏览本单位的全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @return
     */
    List<ProjectStore> getAll(ProjectStoreFlagVo projectStoreFlagVo, User user);

    /**
     * 获取合同显示界面
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    List<ProjectStore> getByUnitIdConstract(ProjectStoreFlagVo projectStoreFlagVo, int unitId);

    /**
     * 获取竣工界面显示
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    List<ProjectStore> getByUnitIdFinish(ProjectStoreFlagVo projectStoreFlagVo, int unitId);

    /**
     * 设置重点项目
     * @param projectId
     * @return
     */
    void updateImportant(int projectId);

    /**
     * 更新项目建议
     * @param projectStore
     */
    void updateSuggestion(ProjectStore projectStore);

    /**
     * 获取全部项目的数量统计
     * @return
     */
    NumStatisticsMo getNumStatistics();

    List<ProjectStore> getProjectLocation();

    /**
     * 获取部分项目的坐标和部分信息（地图显示）
     * @param flag
     * @return
     */
    List<ProjectStore> getProjectLocationClass(int flag);

    /**
     * 获取全部单位的项目统计
     * @return
     */
    List<ConstructUnitStatisticMo> getConstructUnitStatistic();
}
