package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.PredesignReport;

public class PredesignReportWithProject extends PredesignReport {
    private String projectName;

    private String buildYear;

    private String name;

    public String getProjectName(){return projectName;}

    public void  setProjectName(String projectName){this.projectName=projectName;}

    public String getBuildYear(){return buildYear;}

    public void setBuildYear(String buildYear){this.buildYear=buildYear;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}
}
