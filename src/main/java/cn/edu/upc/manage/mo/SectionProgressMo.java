package cn.edu.upc.manage.mo;

import java.util.List;

public class SectionProgressMo {
    private int sectionId;

    private String sectionName;

    private List<ConstructionProgressMo> constructionProgressMoList;

    private int projectId;

    public List<ConstructionProgressMo> getConstructionProgressMoList() {
        return constructionProgressMoList;
    }

    public void setConstructionProgressMoList(List<ConstructionProgressMo> constructionProgressMoList) {
        this.constructionProgressMoList = constructionProgressMoList;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
