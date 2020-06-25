package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.PlanGuide;

import java.util.List;

public interface PlanGuideService {
    public void insertProjectGuide(PlanGuide planGuide);
    public List<PlanGuide> selectPlanGuide();
    public void updatePlanGuide(PlanGuide recordUp);
}
