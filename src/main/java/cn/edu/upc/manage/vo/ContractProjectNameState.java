package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ContractWithProjectName;

/**
 * @author 董志涵
 */
public class ContractProjectNameState extends ContractWithProjectName {
    private int state;

    public int getState(){return state;}

    public void setState(int state){this.state=state;}
}
