package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ProjectStore;


/**
 * @author 董志涵
 */
public class ProjectStoreApprove extends ProjectStore {
    private int approve2;

    private int examine;

    public int getExamine (){return examine;}

    public void setApprove2(int approve2){this.approve2=approve2;}

    public void setExamine(int examine){this.examine=examine;}

    public int getApprove2(){return approve2;}
}
