package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.wwp.service.ProjectStoreService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/projectStore",method={RequestMethod.POST, RequestMethod.GET})
public class ProjectStoreController {
    @Autowired
    public ProjectStoreService projectStoreService;


    @RequestMapping("/getProjectStoreList")

    @ResponseBody
    public CommonReturnType getProjectStoreList(HttpSession session, @RequestBody JSONObject jsonObject){
//        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        String projectName = jsonObject.getString("projectName");
        List<ProjectStore> list1= projectStoreService.selectProjectStore(unitId,projectName);
        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/searchProjectStoreList")

    @ResponseBody
    public CommonReturnType searchProjectStoreList(@RequestBody ProjectStore projectStore){
        List<ProjectStore> list2= projectStoreService.searchProjectStore(projectStore);
        return  CommonReturnType.create(list2,"查询成功");
    }

    @RequestMapping("/getProjectStoreByUnitId")
    @ResponseBody
    public CommonReturnType getProjectStoreByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        List<ProjectStore> list2= projectStoreService.getProjectStoreByUnitId(unitId);
        return  CommonReturnType.create(list2,"查询成功");
    }

    /**
     * 获取本单位可以招标的项目
     * @param session
     * @return
     */
    @RequestMapping("/getCanTenderByUnitId")
    @ResponseBody
    public CommonReturnType getCanTenderByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectStoreService.getCanTenderByUnitId(unitId),"查询成功");
    }

    /**
     * 获取本单位可以填报合同的项目
     * @param session
     * @return
     */
    @RequestMapping("/getCanContractByUnitId")
    @ResponseBody
    public CommonReturnType getCanContractByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectStoreService.getCanTenderByUnitId(unitId),"查询成功");
    }

    /**
     * 获取本单位可以填报进度的项目
     * @param session
     * @return
     */
    @RequestMapping("/getCanProgressByUnitId")
    @ResponseBody
    public CommonReturnType getCanProgressByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectStoreService.getCanTenderByUnitId(unitId),"查询成功");
    }


    /**
     * 获取本单位可以填报初步设计的项目
     * @param session
     * @return
     */
    @RequestMapping("/getCanDesignByUnitId")
    @ResponseBody
    public CommonReturnType getCanDesignByUnitId(HttpSession session){
        //        int unitId= SysUser.getCurrentUserUnitId(session);
        int unitId=1;
        return  CommonReturnType.create(projectStoreService.getCanDesignByUnitId(unitId),"查询成功");
    }

}
