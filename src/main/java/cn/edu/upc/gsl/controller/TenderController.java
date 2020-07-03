package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.TenderInformationService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.TenderInformation;
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
     * 修改
     * @param tenderInformation
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public  CommonReturnType  updateTender(@RequestBody TenderInformation tenderInformation){
        tenderInformationService.updateTender(tenderInformation);
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

    @RequestMapping(value = "/selectByProjectId")
    @ResponseBody
    public CommonReturnType selectByProjectId(@RequestParam(value="projectId") Integer projectId){
        List<TenderInformation> tenderInformationList = tenderInformationService.selectByProjectId(projectId);
        return CommonReturnType.create(tenderInformationList);
    }

    @RequestMapping(value = "/delByProjectId")
    @ResponseBody
    public CommonReturnType delByProjectId(@RequestParam(value = "projectId")Integer projectId){
        tenderInformationService.delTenderByProjectId(projectId);
        return CommonReturnType.create(null,"删除完成");
    }

    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody TenderInformation tenderInformation ){
        tenderInformationService.updateTender(tenderInformation);
        return CommonReturnType.create(null,null,0,"审核成功");
    }

    @RequestMapping("/getTenderContractState")
    @ResponseBody
    public CommonReturnType getTenderContractState(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");

        return CommonReturnType.create(tenderInformationService.getTenderContractState(projectId));
    }


}
