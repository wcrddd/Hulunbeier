package cn.edu.upc.gsl.service.impl;


import cn.edu.upc.gsl.service.GuideService;
import cn.edu.upc.manage.dao.GuideMapper;
import cn.edu.upc.manage.model.Guide;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author gsl
 * @date 2020/5/13
 */
@Service
public class GuideServiceImpl implements GuideService {

    @Resource
    GuideMapper guideMapper;


    @Override
    public List<Guide> getGuideList(String title, String documentId) {
        List<Guide> guideList;
        //当这没有传参时和当传参为空时，都返回全部指南
        if (title == null || documentId ==null) {
            guideList = guideMapper.selectAllGuide();
        } else if ("".equals(title) && "".equals(documentId)) {
            guideList = guideMapper.selectAllGuide();
        } else if ((!"".equals(title)) && "".equals(documentId)) {
            //只根据标题查询
            guideList = guideMapper.selectByTitle(title);
        } else if (!"".equals(title)) {
            //根据两个参数查
            guideList = guideMapper.selectByTitleAndDocumentId(title, documentId);
        } else {
            //只根据文件号查
            guideList = guideMapper.selectByDocumentId(documentId);
        }
        return guideList;
    }

}
