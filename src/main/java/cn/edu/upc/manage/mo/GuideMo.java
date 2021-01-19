package cn.edu.upc.manage.mo;

import cn.edu.upc.manage.model.ConstructionUnit;
import cn.edu.upc.manage.model.Guide;

import java.util.List;

public class GuideMo {
    private Guide guide;

    private List<ConstructionUnit> constructionUnitList;

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public List<ConstructionUnit> getConstructionUnitList() {
        return constructionUnitList;
    }

    public void setConstructionUnitList(List<ConstructionUnit> constructionUnitList) {
        this.constructionUnitList = constructionUnitList;
    }
}
