package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.manage.dao.GuideMapper;
import cn.edu.upc.manage.dao.GuideUnitRelationMapper;
import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuideService2Impl implements GuideService2 {
    @Autowired
    private GuideMapper guideMapper;
    @Autowired
    private GuideUnitRelationMapper guideUnitRelationMapper;

    @Transactional
    @Override
    public int insertGuide(Guide guide){
         guideMapper.insertSelective(guide);
         return guideMapper.selectLastInsert();
    }

    @Transactional
    @Override
    public void inserGuideUnitRelation(GuideUnitRelation guideUnitRelation){
        guideUnitRelationMapper.insertSelective(guideUnitRelation);
    }

    @Override
    public List<Guide> getGuideByUnitId(int unitId){
        return guideMapper.getGuideByUnitId(unitId);
    }
}
