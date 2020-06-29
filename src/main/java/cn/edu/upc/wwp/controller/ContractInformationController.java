package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ContractInformation;
import cn.edu.upc.manage.model.ContractInformationWithTenderId;
import cn.edu.upc.wwp.service.ContractInformationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/contractInformation",method={RequestMethod.POST, RequestMethod.GET})
public class ContractInformationController {
    @Autowired
    ContractInformationService contractInformationService;

    @RequestMapping("/updateContractInformation")

    @ResponseBody
    public CommonReturnType update(@RequestBody ContractInformation recordUp){

        contractInformationService.updateContractInformation(recordUp);

        return CommonReturnType.create(null,null,0,"更新成功");
    }

    @RequestMapping("/insertContractInformation")

    @ResponseBody
    public CommonReturnType insert(@RequestBody ContractInformationWithTenderId recordIn){
        int id=contractInformationService.insertContractInformation(recordIn);
        return CommonReturnType.create(id);
    }

    @RequestMapping("/deleteContractInformation")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody ContractInformation recordDel){
        contractInformationService.deleteFlag(recordDel);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

    @RequestMapping("/getAllContractInformation")

    @ResponseBody
    public CommonReturnType getAllContractInformation(){
        return CommonReturnType.create(contractInformationService.getAllContractInformation());
    }

//    @RequestMapping("/insertNewContract")
//    @ResponseBody
//    public CommonReturnType insertNewContract(@RequestBody ContractInformation recordIn){
//        contractInformationService.insertContractInformation(recordIn);
//        return CommonReturnType.create();
//    }

    @RequestMapping("/getContractByProjectId")
    @ResponseBody
    public CommonReturnType getContractByProjectId(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return CommonReturnType.create(contractInformationService.getContractByProjectId(projectId));
    }

    @RequestMapping("/getContractBytId")
    @ResponseBody
    public CommonReturnType getContractBytId(@RequestBody JSONObject jsonObject){
        int id=jsonObject.getInteger("id");
        return CommonReturnType.create(contractInformationService.getContractBytId(id));
    }

    @RequestMapping("/getContractStatistics")
    @ResponseBody
    public CommonReturnType getContractStatistics(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return CommonReturnType.create(contractInformationService.getContractStatistics(projectId));
    }

    @RequestMapping("/getContractByTenderId")
    @ResponseBody
    public CommonReturnType getContractByTenderId(@RequestBody JSONObject jsonObject){
        int tenderId=jsonObject.getInteger("tenderId");
        return CommonReturnType.create(contractInformationService.getContractByTenderId(tenderId));
    }

    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody  ContractInformation contractInformation ){
        contractInformationService.updateContractInformation(contractInformation);
        return CommonReturnType.create(null,null,0,"审核成功");
    }

    @RequestMapping("/getAllContractWithProjectName")
    @ResponseBody
    public CommonReturnType getAllContractWithProjectName(){
        return CommonReturnType.create(contractInformationService.getAllContractWithProjectName());
    }

}
