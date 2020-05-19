package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.GuideServiceAudit;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Guide;
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
    GuideServiceAudit guideServiceAudit;
    /**
     * 项目单位（查询）获取指南列表
     * @param guide
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public CommonReturnType getGuides(@RequestBody Guide guide) {
        String title = guide.getTitle();
        String documentId = guide.getDocumentId();
        List<Guide> guideList = guideServiceAudit.getGuideList(title,documentId);
        return CommonReturnType.create(guideList);
    }
}
