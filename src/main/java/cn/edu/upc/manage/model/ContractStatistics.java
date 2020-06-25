package cn.edu.upc.manage.model;

public class ContractStatistics {
    private int projectId;
    private int signedNum;
    private String signedMoney;
    private int unsignedNum;
    private float unsignedMoney;
    private String projectName;

    public int getProjectId(){return projectId;}

    public void setProjectId(int projectId){this.projectId=projectId;}

    public int getSignedNum(){return signedNum;}

    public void setSignedNum(int signedNum){this.signedNum=signedNum;}

    public String getSignedMoney(){return signedMoney;}

    public void setSignedMoney(String signedMoney){this.signedMoney=signedMoney;}

    public int getUnsignedNum(){return unsignedNum;}

    public void setUnsignedNum(int unsignedNum){this.unsignedNum=unsignedNum;}

    public float getUnsignedMoney(){return unsignedMoney;}

    public void setUnsignedMoney(float unsignedMoney){this.unsignedMoney=unsignedMoney;}

    public String getProjectName(){return projectName;}

    public void setProjectName(String projectName){this.projectName=projectName;}
}
