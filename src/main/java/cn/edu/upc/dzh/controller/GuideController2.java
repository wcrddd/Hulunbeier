package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.GuideTitleA;
import cn.edu.upc.manage.vo.GuideUnitVo;
import cn.edu.upc.manage.vo.GuideVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    public CommonReturnType insertGuide(@RequestBody GuideUnitVo guideUnitVo) {
//        Guide guide = new Guide();
////        guide.setTitle(param.getString("title"));
////        guide.setDocumentId(param.getString("documentId"));
////        guide.setPublishTime(param.getDate("publishTime"));
////        guide.setEndTime(param.getDate("endTime"));
////        guide.setContent(param.getString("content"));
////        guide.setAppendix(param.getString("appendix"));
//        BeanUtils.copyProperties(guideUnitVo,guide);
//        int guideId = guideService2.insertGuide(guide);
////        JSONArray unitId;
//        Integer[] unitId = guideUnitVo.getUnitId();
//        GuideUnitRelation guideUnitRelation = new GuideUnitRelation();
//        guideUnitRelation.setGuideId(guideId);
//        for(Object uId: unitId){
//            guideUnitRelation.setUnitId((Integer) uId);
//            guideService2.inserGuideUnitRelation(guideUnitRelation);
//        }
//        for (int i = 0; i < unitId.length; i++) {
//            guideUnitRelation.setUnitId((int) unitId[i]);
//            guideService2.inserGuideUnitRelation(guideUnitRelation);
//        }
        guideService2.insertGuide(guideUnitVo);
        return CommonReturnType.create("新增成功");
    }

    @RequestMapping("/getGuideByUnitId")
    @ResponseBody
    public CommonReturnType getGuideByUnitId(HttpSession session) throws ParseException {
//        int userId= SysUser.getCurrentUserUnitId(session);
        User user = (User) session.getAttribute("user");
//        int unitId=user.getDepartmentUnitId();
        if(user.getUserType()==2){
//            int unitId = 1;
            List<Guide> guideList = guideService2.getGuideByUnitId(user.getDepartmentUnitId());
            return CommonReturnType.create(guideList);
        }else{
            return CommonReturnType.create(guideService2.getAllGuide());
        }

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
    public CommonReturnType selectGuide(@RequestBody JSONObject param,HttpSession session) {
//        guideService.insertGuide(guide);
//        int unitId = 1;
        int unitId=SysUser.getCurrentUserUnitId(session);
        int userType=SysUser.getUserType(session);
        String title = param.getString("title");
        String documentId = param.getString("documentId");
        if(userType==2){
            List<Guide> guideList = guideService2.selectGuide(unitId, title, documentId);
            return CommonReturnType.create(guideList);
        }else {
            List<Guide> guideList = guideService2.selectAllGuide( title, documentId);
            return CommonReturnType.create(guideList);
        }


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

    @RequestMapping("/importExcel")
    @ResponseBody
    public CommonReturnType importExcel(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
        GuideVo guideVo = new GuideVo();
//        if(fileName.matches("^.+\\.(?i)(xls)$")){//03版本excel,xls
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"该文件类型已不支持，请使用07版本后缀为.xlsx版本导入");
//        }else if (fileName.matches("^.+\\.(?i)(xlsx)$")){//07版本,xlsx
            guideVo=guideService2.importExcel(file);
//        }
        return CommonReturnType.create(guideVo);
    }

    @RequestMapping("/getOption")
    @ResponseBody
    public CommonReturnType getOption(int projectId){
        List<GuideTitleA> guideTitleAList = new ArrayList<>();
        guideTitleAList = guideService2.getOption(projectId);
        return CommonReturnType.create(guideTitleAList);
    }
}
