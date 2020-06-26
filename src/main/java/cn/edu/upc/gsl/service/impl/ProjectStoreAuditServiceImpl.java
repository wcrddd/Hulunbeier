package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gsl
 * @date 2020/5/19
 */
@Service
public class ProjectStoreAuditServiceImpl implements ProjectStoreAuditService {

    @Resource
    ProjectStoreMapper projectStoreMapper;

    /**
     * 新增项目申报
     *
     * @param projectStore
     */
    @Override
    public int addProject(ProjectStore projectStore) {
        return projectStoreMapper.insertSelective(projectStore);
    }

    /**
     * 查询项目
     *
     * @param projectName
     * @param buildYear
     * @return
     */
    @Override
    public List<ProjectStore> getProject(String projectName, String buildYear) {
        System.out.println("projectName:"+projectName+" buildYear:"+buildYear);
        List<ProjectStore> projectStoreList;
        //当没有传参时和当传参为空时，都返回全部项目
        if (projectName == null || buildYear == null) {
            projectStoreList = projectStoreMapper.selectAllProject();
        } else if ("".equals(projectName) && "".equals(buildYear)) {
            projectStoreList = projectStoreMapper.selectAllProject();
        } else if ((!"".equals(projectName)) && "".equals(buildYear)) {
            //只根据项目名称查询
            projectStoreList = projectStoreMapper.selectByProjectName(projectName);
        } else if (!"".equals(projectName)) {
            //根据两个参数查
            projectStoreList = projectStoreMapper.selectByProjectNameAndBuildYear(projectName, buildYear);
        } else {
            //只根据年份查
            projectStoreList = projectStoreMapper.selectByBuildYear(buildYear);
        }
        return projectStoreList;
    }

    /**
     * 跟新项目状态
     * @param record
     */
    @Override
    public void updateState(ProjectStore record) {
        projectStoreMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 改为只显示审批通过的
     * @return
     */
    @Override
    public List<ProjectStore> selectProjectPass() {
        return  projectStoreMapper.selectProjectPass();
    }

    /**
     * 根据项目id查询
     * @param id
     * @return
     */
    @Override
    public ProjectStore selectProjectById(Integer id) {
        return projectStoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(ProjectStore projectStore) {
        projectStoreMapper.updateByPrimaryKeySelective(projectStore);
    }

    /**
     * 区分已计划申报的和未进行计划申报的项目
     *
     * @param httpServletRequest
     * @return
     */
    @Override
    public List<ProjectStoreVo> divideProjectPlan(HttpServletRequest httpServletRequest) {
        //获取session
        //User user = (User) httpServletRequest.getSession().getAttribute("user");
        //Integer departmentUnitId = user.getDepartmentUnitId();

        Integer departmentUnitId = 1;
        List<ProjectStoreVo> allPassedProject= projectStoreMapper.selectPassProjectByUnitId(departmentUnitId);
        List<ProjectStoreVo> projectPlaned = projectStoreMapper.selectProjectPlaned(departmentUnitId);
        System.out.println(allPassedProject.containsAll(projectPlaned));
        for (ProjectStoreVo projectStoreVoPlan : projectPlaned){
            //如果查出来有，则设置标志位为0，不可进行申报

           for(ProjectStoreVo projectStoreVoPass : allPassedProject){
               Boolean b = (projectStoreVoPlan.getId()).equals(projectStoreVoPass.getId());
               System.out.println(b);
               if((projectStoreVoPlan.getId()).equals(projectStoreVoPass.getId())){
                   projectStoreVoPass.setCanPlan(0);
               }else {
                   projectStoreVoPass.setCanPlan(1);
               }
            }
        }
        return allPassedProject;
    }
}
