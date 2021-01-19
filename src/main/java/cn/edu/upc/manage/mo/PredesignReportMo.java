package cn.edu.upc.manage.mo;

import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.ProjectSection;

import java.util.List;

public class PredesignReportMo extends PredesignReport {
    private List<ProjectSection> projectSectionList;

    public List<ProjectSection> getProjectSectionList() {
        return projectSectionList;
    }

    public void setProjectSectionList(List<ProjectSection> projectSectionList) {
        this.projectSectionList = projectSectionList;
    }
}
