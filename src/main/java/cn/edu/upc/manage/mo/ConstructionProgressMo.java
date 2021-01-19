package cn.edu.upc.manage.mo;

import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.model.ConstructionProgressAppendix;

import java.util.List;

/**
 * @author 董志涵 2020-12-02
 */
public class ConstructionProgressMo extends ConstructionProgress {
    private List<ConstructionProgressAppendix> constructionProgressAppendixList;

    public List<ConstructionProgressAppendix> getConstructionProgressAppendixList() {
        return constructionProgressAppendixList;
    }

    public void setConstructionProgressAppendixList(List<ConstructionProgressAppendix> constructionProgressAppendixList) {
        this.constructionProgressAppendixList = constructionProgressAppendixList;
    }
}
