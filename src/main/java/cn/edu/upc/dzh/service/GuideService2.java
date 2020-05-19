package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;

import java.util.List;

public interface GuideService2 {
    public int insertGuide(Guide guide);
    public void inserGuideUnitRelation(GuideUnitRelation guideUnitRelation);
    public List<Guide> getGuideByUnitId(int unitId);
}
