package cn.edu.upc.manage.model;

public class ProjectStoreWithUnitName extends ProjectStore {
    private String constructionUnitName;

    private String guideName;

    private String applyTypeName;

    public String getConstructionUnitName(){return constructionUnitName;}

    public void setConstructionUnitName(String constructionUnitName){this.constructionUnitName=constructionUnitName;}

    @Override
    public String getGuideName(){return guideName;}

    @Override
    public void setGuideName(String guideName){this.guideName=guideName;}

    public String getApplyTypeName(){return applyTypeName;}

    public void setApplyTypeName(String applyTypeName){this.applyTypeName=applyTypeName;}
}
