package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.ProjectStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 通过和未通过
     * @return
     */
    @Override
    public List<ProjectStore> selectProjectPassAndNo() {
        return  projectStoreMapper.selectProjectPassAndNo();
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
}
