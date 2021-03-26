package cn.edu.upc.manage.mo;

import cn.edu.upc.manage.model.ProjectStore;

/**
 * @author 董志涵 2020-12-28
 */
public class ProjectStoreMo extends ProjectStore {
    private int planId;

    private float contractMoney;

    private float spendMoney;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public float getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(float contractMoney) {
        this.contractMoney = contractMoney;
    }

    public float getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(float spendMoney) {
        this.spendMoney = spendMoney;
    }
}
