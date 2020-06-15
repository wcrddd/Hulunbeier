package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.UnitService;
import cn.edu.upc.manage.dao.ConstructionUnitMapper;
import cn.edu.upc.manage.model.ConstructionUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private ConstructionUnitMapper constructionUnitMapper;

    @Override
    public List<ConstructionUnit> getConstructionUnit(){
        return constructionUnitMapper.getConstructionUnit();
    }

    @Override
    public void addUnit(ConstructionUnit constructionUnit) {
        constructionUnitMapper.insertSelective(constructionUnit);
    }

    @Override
    public void updateUnit(ConstructionUnit constructionUnit) {
        constructionUnitMapper.updateByPrimaryKeySelective(constructionUnit);
    }

    @Override
    public void delUnit(Integer id) {
        constructionUnitMapper.updateDelFlag(id);
    }
}
