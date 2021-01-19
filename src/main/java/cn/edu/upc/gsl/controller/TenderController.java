package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.TenderInformationService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectSection;
import cn.edu.upc.manage.model.TenderInformation;
import cn.edu.upc.manage.vo.ProjectSectionVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>manage</h3>
 * <p>招标信息</p>
 * @author : gsl
 * @date : 2020-06-12 16:39
 **/
@Controller
@CrossOrigin
@RequestMapping(value = "/tender")
public class TenderController {

    @Autowired
    TenderInformationService tenderInformationService;
    /**
     * 新增招标
     * @param tenderInformation
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public  CommonReturnType addTender(@RequestBody TenderInformation tenderInformation){
        tenderInformationService.addTender(tenderInformation);
        return CommonReturnType.create(null,"招标信息增加成功");
    }

    /**
     * 删除（软删除）
     * @param id
     * @return
     * 如果只传一两个单独的参数时，用 @RequestParam方便
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonReturnType deleteTender(@RequestParam("id") Integer id){
        tenderInformationService.deleteTender(id);
        return  CommonReturnType.create(null,"删除成功");
    }

    /**
     * 修改(改2020-12-01)
     * @param projectSectionList
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public  CommonReturnType  updateTender(@RequestBody List<ProjectSection> projectSectionList){
        tenderInformationService.updateTender(projectSectionList);
        return CommonReturnType.create(null,"修改成功");
    }

    /**
     * 获取全部招标信息，带对应的项目名称
     * @return
     */
    @RequestMapping(value = "/getAllTender")
    @ResponseBody
    public  CommonReturnType  getAllTender(){
        return CommonReturnType.create(tenderInformationService.getAllTender());
    }

    /**
     * 根据id查询招标信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/select")
    @ResponseBody
    public CommonReturnType select(@RequestParam(value = "id") Integer id){
        TenderInformation tenderInformation = tenderInformationService.select(id);
        return CommonReturnType.create(tenderInformation);
    }

    /**
     * 根据项目id获取招标（改2020-12-01）
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/selectByProjectId")
    @ResponseBody
    public CommonReturnType selectByProjectId(@RequestParam(value="projectId") Integer projectId){
        List<ProjectSection> tenderInformationList = tenderInformationService.selectByProjectId(projectId);
        return CommonReturnType.create(tenderInformationList);
    }

    @RequestMapping(value = "/delByProjectId")
    @ResponseBody
    public CommonReturnType delByProjectId(@RequestParam(value = "projectId")Integer projectId){
        tenderInformationService.delTenderByProjectId(projectId);
        return CommonReturnType.create(null,"删除完成");
    }

    /**
     * 审批（改2020-12-01）
     * @param projectSection
     * @return
     */
    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody ProjectSection projectSection){
        tenderInformationService.updateTender2(projectSection);
        return CommonReturnType.create(null,"审核成功");
    }

    @RequestMapping("/getTenderContractState")
    @ResponseBody
    public CommonReturnType getTenderContractState(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");

        return CommonReturnType.create(tenderInformationService.getTenderContractState(projectId));
    }

    /**
     * 新增招标
     * @param projectSectionList
     * @return
     */
    @RequestMapping(value = "/add2")
    @ResponseBody
    public  CommonReturnType addTender2(@RequestBody List<ProjectSection> projectSectionList){
        tenderInformationService.addTender2(projectSectionList);
        return CommonReturnType.create(null,"招标信息增加成功");
    }


}
