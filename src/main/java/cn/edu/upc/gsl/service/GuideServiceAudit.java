package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.Guide;

import java.util.List;

/**
 * @author gsl
 * @date
 */
public interface GuideServiceAudit {

    /**
     * 获取指南列表
     * @param guideTitle
     * @param guideNumber
     * @return
     */
    List<Guide> getGuideList(String guideTitle, String guideNumber);
}
