package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ProjectTypeMapper;
import cn.edu.upc.manage.model.ProjectType;
import cn.edu.upc.wwp.service.ProjectTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("projectTypeService")
public class ProjectTypeServiceImpl implements ProjectTypeService {
    @Resource
    ProjectTypeMapper projectTypeMapper;
    @Override
    public List<ProjectType> selectProjectType() {
        return projectTypeMapper.selectProjectType();
    }

    @Override
    public void updateProjectType(ProjectType recordUp) {
        recordUp.setOperator("test");
        projectTypeMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public void insertProjectType(ProjectType recordIn) {

        recordIn.setOperator("test");
        projectTypeMapper.insertSelective(recordIn);
    }

    @Override
    public void deleteFlag(ProjectType recordDel) {
        ProjectType result=projectTypeMapper.selectByPrimaryKey(recordDel.getId());
        if (result!=null)
        {
            recordDel.setDelFlag(1);
            projectTypeMapper.updateByPrimaryKeySelective(recordDel);
        }
    }
}
