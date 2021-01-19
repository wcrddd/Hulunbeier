package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.mo.GuideMo;
import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.GuideTitleA;
import cn.edu.upc.manage.vo.GuideUnitVo;
import cn.edu.upc.manage.vo.GuideVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public CommonReturnType getGuideByUnitId(HttpSession session, HttpServletRequest httpServletRequest) throws ParseException {
        String token = httpServletRequest.getHeader("token");
        User user = (User) session.getAttribute(token);
        if(user.getUserType()==2){
//            int unitId = 1;
            List<Guide> guideList = guideService2.getGuideByUnitId(user.getDepartmentUnitId());
            return CommonReturnType.create(guideList);
        }else{
            return CommonReturnType.create(guideService2.getAllGuide());
        }
//        List<Guide> guideList = guideService2.getGuideByUnitId(6);
//        return CommonReturnType.create(guideList);

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
    public CommonReturnType selectGuide(@RequestBody JSONObject param,HttpSession session,HttpServletRequest httpServletRequest) {
//        guideService.insertGuide(guide);
//        int unitId = 1;
        int unitId=SysUser.getCurrentUserUnitId(session);
        int userType=SysUser.getUserType(session,httpServletRequest);
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
        return CommonReturnType.create(null,  "删除成功");
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
    public CommonReturnType getOption(int guideId){
        List<GuideTitleA> guideTitleAList = new ArrayList<>();
        guideTitleAList = guideService2.getOption(guideId);
        return CommonReturnType.create(guideTitleAList);
    }

    /**
     * 获取指南以及单位
     * @param guideId
     * @return
     */
    @RequestMapping("/getGuideByUnit")
    @ResponseBody
    public CommonReturnType getGuideByUnit(@Param("guideId") int guideId){
        GuideUnitVo guideMo = guideService2.getGuideByUnit(guideId);
        return CommonReturnType.create(guideMo);
    }

    /**
     * 修改指南
     * @param guideUnitVo
     * @return
     */
    @RequestMapping("/updateGuide")
    @ResponseBody
    public CommonReturnType updateGuide(@RequestBody GuideUnitVo guideUnitVo){
        guideService2.updateGuide(guideUnitVo);
        return CommonReturnType.create("修改成功");
    }


}
