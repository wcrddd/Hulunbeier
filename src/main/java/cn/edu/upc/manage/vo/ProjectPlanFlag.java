package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ProjectPlan;
import cn.edu.upc.wwp.controller.param.ProjectPlanParam;

/**
 * @author 董志涵
 */
public class ProjectPlanFlag extends ProjectPlanParam {
    private Integer planedFlag;

    public Integer getPlanedFlag() {
        return planedFlag;
    }
    public void setPlanedFlag(Integer planedFlag) {
        this.planedFlag = planedFlag;
    }
}
