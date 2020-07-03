package cn.edu.upc.manage.model;

public class ContractWithProjectName extends ContractInformation {
    private String projectName;

    private String tenderDocumentName;

    public String getProjectName(){return projectName;}

    public void setProjectName(String projectName){this.projectName=projectName;}

    public String getTenderDocumentName(){return tenderDocumentName;}

    public void setTenderDocumentName(String tenderDocumentName){this.tenderDocumentName=projectName;}
}
