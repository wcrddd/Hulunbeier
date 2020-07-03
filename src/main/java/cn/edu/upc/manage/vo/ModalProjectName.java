package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.Modal;

/**
 * @author 董志涵
 */
public class ModalProjectName extends Modal {
    private String projectName;

    private String contractName;

    public String getProjectName(){return projectName;}

    public void setProjectName(String projectName){this.projectName=projectName;}

    public String getContractName(){return contractName;}

    public void setContractName(String contractName){this.contractName=contractName;}
}
