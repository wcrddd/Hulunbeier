package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ConstructionMapper;
import cn.edu.upc.manage.model.Construction;
import cn.edu.upc.wwp.service.ConstructionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("constructionService")
public class ConstructionServiceImpl implements ConstructionService {


    @Resource
    ConstructionMapper constructionMapper;

    @Override
    public List<Construction> selectConstruction() {
        return constructionMapper.selectConstruction();
    }

    @Override
    public void updateConstruction(Construction recordUp) {
        recordUp.setOperator("test");
        constructionMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public void insertConstruction(Construction recordIn) {
        recordIn.setOperator("test");
        constructionMapper.insertSelective(recordIn);
    }

    @Override
    public void deleteFlag(Construction recordDel) {
        Construction result=constructionMapper.selectByPrimaryKey(recordDel.getId());
        if (result!=null)
        {
            recordDel.setDelFlag(1);
            constructionMapper.updateByPrimaryKeySelective(recordDel);
        }
    }



}
