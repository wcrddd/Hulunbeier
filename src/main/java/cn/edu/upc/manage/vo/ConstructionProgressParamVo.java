package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.model.ConstructionProgressAppendix;

import java.util.List;

/**
 * @author 董志涵 2020-11-30
 */
public class ConstructionProgressParamVo extends ConstructionProgress {
    private List<ConstructionProgressAppendix> constructionProgressAppendixList;

    public List<ConstructionProgressAppendix> getConstructionProgressAppendixList() {
        return constructionProgressAppendixList;
    }

    public void setConstructionProgressAppendixList(List<ConstructionProgressAppendix> constructionProgressAppendixList) {
        this.constructionProgressAppendixList = constructionProgressAppendixList;
    }
}
