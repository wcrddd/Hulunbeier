package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ProjectStore;

/**
 * <h3>manage</h3>
 * <p>增加标志符，用来前端展示区分</p>
 *
 * @author : gsl
 * @date : 2020-06-25 15:49
 **/
public class ProjectStoreVo extends ProjectStore {


    /**
     * 是否可以进行【计划申报】
     * 规定如下：1 可以进行申报，0不可以进行申报
     * 为 1 时，证明还没有在project_plan里
     * 0 时，证明已经申报过，不可以再申报
     */
    private Integer canPlan;

    public Integer getCanPlan() {
        return canPlan;
    }

    public void setCanPlan(Integer canPlan) {
        this.canPlan = canPlan;
    }
}
