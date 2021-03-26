package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ProjectVerification;

import java.util.List;

/**
 * @author dzh 2021-3-20
 */
public class ProjectVerificationVo {
    private int projectId;

    private List<ProjectVerification> projectVerificationList;

    public List<ProjectVerification> getProjectVerificationList() {
        return projectVerificationList;
    }

    public void setProjectVerificationList(List<ProjectVerification> projectVerificationList) {
        this.projectVerificationList = projectVerificationList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
