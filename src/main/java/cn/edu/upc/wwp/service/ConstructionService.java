package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.Construction;

import java.util.List;

public interface ConstructionService {
    public List<Construction> selectConstruction();
    public void updateConstruction(Construction recordUp);
    public void insertConstruction(Construction recordIn);
    public void deleteFlag(Construction recordDel);
}
