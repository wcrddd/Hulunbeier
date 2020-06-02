package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.GuideService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Guide;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author gsl
 * @date 2020/5/13
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/guide", method = {RequestMethod.GET, RequestMethod.POST})
public class GuideController {

    @Autowired
    GuideService guideService;
    /**
     * 项目单位（查询）获取指南列表
     * 后端分页
     * @param pageNum 页码
     * @param  pageSize 每页大小
     * @param guide
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public CommonReturnType getGuides(@Param("pageNum") Integer pageNum,@Param("pageSize")Integer pageSize, @RequestBody Guide guide) {
        String title = guide.getTitle();
        String documentId = guide.getDocumentId();
        List<Guide> guideList = guideService.getGuideList(pageNum,pageSize,title,documentId);
        return CommonReturnType.create(guideList,"查询成功");
    }
}
