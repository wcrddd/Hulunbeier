package cn.edu.upc.manage.vo;

/**
 * @author 董志涵 2020-12-03
 */
public class ProjectStoreFlagVo {
    private int storeFlag;

    private int planedFlag;

    private int planedFlagA;

    private int planedFlagB;

    private int planedFlagC;

    private int flag;

    private String planYear;

    private String projectName;

    private String place;

    private String buildYear;

    private String thisYear;

    private String projectType;

    private String constructionUnit;

    public int getStoreFlag() {
        return storeFlag;
    }

    public void setStoreFlag(int storeFlag) {
        this.storeFlag = storeFlag;
    }

    public int getPlanedFlag() {
        return planedFlag;
    }

    public void setPlanedFlag(int planedFlag) {
        this.planedFlag = planedFlag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getPlanedFlagA() {
        return planedFlagA;
    }

    public void setPlanedFlagA(int planedFlagA) {
        this.planedFlagA = planedFlagA;
    }

    public int getPlanedFlagB() {
        return planedFlagB;
    }

    public void setPlanedFlagB(int planedFlagB) {
        this.planedFlagB = planedFlagB;
    }

    public int getPlanedFlagC() {
        return planedFlagC;
    }

    public void setPlanedFlagC(int planedFlagC) {
        this.planedFlagC = planedFlagC;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String year) {
        this.buildYear = year;
    }

    public String getThisYear() {
        return thisYear;
    }

    public void setThisYear(String thisYear) {
        this.thisYear = thisYear;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }
}
