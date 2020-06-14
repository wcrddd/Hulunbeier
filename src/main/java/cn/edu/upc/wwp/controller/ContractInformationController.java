package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ContractInformation;
import cn.edu.upc.wwp.service.ContractInformationService;
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

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertContractInformation")

    @ResponseBody
    public CommonReturnType insert(@RequestBody ContractInformation recordIn){
        contractInformationService.insertContractInformation(recordIn);
        return  CommonReturnType.create(null);
    }

    @RequestMapping("/deleteContractInformation")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody ContractInformation recordDel){
        contractInformationService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }


}
