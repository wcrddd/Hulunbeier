package cn.edu.upc.manage.mo;

import cn.edu.upc.manage.model.ProjectStore;

/**
 * @author 董志涵 2020-12-28
 */
public class ProjectStoreMo extends ProjectStore {
    private int planId;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
