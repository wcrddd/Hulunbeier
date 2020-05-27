package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.PlanGuideMapper;
import cn.edu.upc.manage.model.PlanGuide;
import cn.edu.upc.wwp.service.PlanGuideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("planGuideService")
public class PlanGuideServiceImpl implements PlanGuideService {

    @Resource
    PlanGuideMapper planGuideMapper;
    @Override
    public void insertProjectGuide(PlanGuide planGuide) {
        planGuide.setOperator("test");
        planGuideMapper.insertSelective(planGuide);
    }
}