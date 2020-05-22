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


    /**
     * 获取所有指南
     * 查询符合条件的指南
     *
     * @param pageNum 页码号(默认为第1页）
     * @param pageSize 每页大小
     * @param title
     * @param documentId
     * @return
     */
    @Override
    public List<Guide> getGuideList(Integer pageNum,Integer pageSize, String title, String documentId) {
        List<Guide> guideList;

        //简单分页功能
        //默认页码为第一页，默认每页大小10
        Integer defaultPageNum = 1;
        Integer defaultPageSize = 10;
        if (pageNum == null) {
            pageNum = defaultPageNum;
        }
        if(pageSize == null){
            pageSize = defaultPageSize;
        }
        Integer startLine = (pageNum - 1) * pageSize;


        //当这没有传参时和当传参为空时，都返回全部指南
        if (title == null || documentId == null) {
            guideList = guideMapper.selectAllGuide(startLine, pageSize);
        } else if ("".equals(title) && "".equals(documentId)) {
            guideList = guideMapper.selectAllGuide(startLine, pageSize);
        } else if ((!"".equals(title)) && "".equals(documentId)) {
            //只根据标题查询(不分页)
            guideList = guideMapper.selectByTitle(title);
        } else if (!"".equals(title)) {
            //根据两个参数查(不分页)
            guideList = guideMapper.selectByTitleAndDocumentId(title, documentId);
        } else {
            //只根据文件号查(不分页)
            guideList = guideMapper.selectByDocumentId(documentId);
        }
        return guideList;
    }


}
