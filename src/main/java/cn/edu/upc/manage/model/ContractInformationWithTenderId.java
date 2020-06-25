package cn.edu.upc.manage.model;

public class ContractInformationWithTenderId extends ContractInformation {
    private int tenderId;

    public int getTenderId(){return tenderId;}

    public void setTenderId(int tenderId){this.tenderId=tenderId;}
}
