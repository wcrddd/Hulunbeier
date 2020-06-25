package cn.edu.upc.wwp.controller.param;

public class ProjectPlanParam {

    private Integer id;
    private Integer projectId;
    private String  content;
    private Float projectEstimate;
    private String moneySource;
    private Integer  approve;
    private Integer examine;
    private Integer delFlag;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getProjectEstimate() {
        return projectEstimate;
    }

    public void setProjectEstimate(Float projectEstimate) {
        this.projectEstimate = projectEstimate;
    }

    public String getMoneySource() {
        return moneySource;
    }

    public void setMoneySource(String moneySource) {
        this.moneySource = moneySource;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Integer getExamine() {
        return examine;
    }

    public void setExamine(Integer examine) {
        this.examine = examine;
    }
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
