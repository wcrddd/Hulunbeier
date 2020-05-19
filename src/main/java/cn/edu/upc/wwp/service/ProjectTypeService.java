package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.ProjectType;

import java.util.List;

public interface ProjectTypeService {

    public List<ProjectType> selectProjectType();
    public void updateProjectType(ProjectType recordUp);
    public void insertProjectType(ProjectType recordIn);
    public void deleteFlag(ProjectType recordDel);
}
