package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;
import cn.edu.upc.manage.model.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/guide", method = {RequestMethod.POST, RequestMethod.GET})
public class GuideController2 {
    @Autowired
    private GuideService2 guideService2;
    @Autowired
    private UserService userService;

    //插入一条新的指南
    @RequestMapping("/insertGuide")
    @ResponseBody
    public CommonReturnType insertGuide(@RequestBody JSONObject param) {
        Guide guide = new Guide();
        guide.setTitle(param.getString("title"));
        guide.setDocumentId(param.getString("documentId"));
        guide.setPublishTime(param.getDate("publishTime"));
        guide.setEndTime(param.getDate("endTime"));
        guide.setContent(param.getString("content"));
        guide.setAppendix(param.getString("appendix"));
        int guideId = guideService2.insertGuide(guide);
        JSONArray unitId;
        unitId = param.getJSONArray("unitId");
        GuideUnitRelation guideUnitRelation = new GuideUnitRelation();
        guideUnitRelation.setGuideId(guideId);
        for(Object uId: unitId){
            guideUnitRelation.setUnitId((Integer) uId);
            guideService2.inserGuideUnitRelation(guideUnitRelation);
        }
        for (int i = 0; i < unitId.size(); i++) {
            guideUnitRelation.setUnitId((int) unitId.get(i));
            guideService2.inserGuideUnitRelation(guideUnitRelation);
        }
        return CommonReturnType.create("新增成功");
    }

    @RequestMapping("/getGuideByUnitId")
    @ResponseBody
    public CommonReturnType getGuideByUnitId(HttpSession session) throws ParseException {
        int userId = 1;
//        int userId= SysUser.getCurrentUserUnitId(session);
        User user = userService.selectByPrimaryKey(userId);
//        int unitId=user.getDepartmentUnitId();
        int unitId = 1;
        List<Guide> guideList = guideService2.getGuideByUnitId(unitId);
        return CommonReturnType.create(guideList);
    }

    @RequestMapping("/getGuideByUnitId2")
    @ResponseBody
    public CommonReturnType getGuideByUnitId2(HttpSession session) throws ParseException {

        int unitId = SysUser.getCurrentUserUnitId(session);

        List<Guide> guideList = guideService2.getGuideByUnitId(unitId);
        return CommonReturnType.create(guideList);
    }

    @RequestMapping("/selectGuide")//
    @ResponseBody
    public CommonReturnType selectGuide(@RequestBody JSONObject param) {
//        guideService.insertGuide(guide);
        int unitId = 1;
        String title = param.getString("title");
        String documentId = param.getString("documentId");
        List<Guide> guideList = guideService2.selectGuide(unitId, title, documentId);
        return CommonReturnType.create(guideList);
    }

    @RequestMapping("/deleteGuide")//
    @ResponseBody
    public CommonReturnType deleteGuide(@RequestBody JSONObject param) {
//        guideService.insertGuide(guide);
        int guideId = param.getInteger("guideId");
        guideService2.deleteGuide(guideId);
        return CommonReturnType.create(null, null, 0, "删除成功");
    }

    @RequestMapping("/getAllGuide")
    @ResponseBody
    public CommonReturnType getAllGuide() throws ParseException {

        List<Guide> guideList = guideService2.getAllGuide();
        return CommonReturnType.create(guideList);
    }
}
