package cn.edu.upc.manage.vo;

/**
 * @author 董志涵
 */
public class ProjectPlanDesign extends ProjectPlanFlag {
    private int prApprove;
    private int prExamine;

    public int getPrApprove(){return prApprove;}

    public void setPrApprove(int prApprove){this.prApprove=prApprove;}

    public int getPrExamine(){return prExamine;}

    public void setPrExamine(int prExamine){this.prExamine=prExamine;}
}
