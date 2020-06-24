package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.ConstructionUnit;

import java.util.List;

public interface UnitService {
    public List<ConstructionUnit> getConstructionUnit();

    void addUnit(ConstructionUnit constructionUnit);

    void updateUnit(ConstructionUnit constructionUnit);

    void delUnit(Integer id);

    ConstructionUnit selectUnitById(Integer id);
}
