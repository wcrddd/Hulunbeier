package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.Guide;

import java.util.List;

/**
 * @author gsl
 * @date
 */
public interface GuideService {

    /**
     * 获取指南列表(分页)
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @param guideTitle
     * @param guideNumber
     * @return
     */
    List<Guide> getGuideList(Integer pageNum, Integer pageSize,String guideTitle, String guideNumber);


}
