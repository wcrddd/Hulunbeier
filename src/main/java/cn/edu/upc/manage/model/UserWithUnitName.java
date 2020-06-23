package cn.edu.upc.manage.model;

public class UserWithUnitName extends User {
    private String userTypeName;

    private String unitName;

    private String roleName;

    public String getUserTypeName(){return userTypeName;}

    public void setUserTypeName(String userTypeName){this.userTypeName=userTypeName;}

    public String getUnitName(){return unitName;}

    public void setUnitName(String unitName){this.unitName=unitName;}

    public String getRoleName(){return roleName;}

    public void setRoleName(String roleName){this.roleName=roleName;}
}
