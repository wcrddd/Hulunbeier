package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.TenderInformation;

/**
 * @author 董志涵
 */
public class TenderInformationContractState extends TenderInformation {
    private int contractState;

    public int getContractState(){return contractState;}

    public void setContractState(int contractState){this.contractState=contractState;}
}
