package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.ProjectSection;

import java.util.List;

/**
 * @author 董志涵 2020-11-27
 */
public class PredesignReportVo extends PredesignReport {
    private List<ProjectSection> projectSectionList;

    public List<ProjectSection> getProjectSectionList() {
        return projectSectionList;
    }

    public void setProjectSectionList(List<ProjectSection> projectSectionList) {
        this.projectSectionList = projectSectionList;
    }
}
