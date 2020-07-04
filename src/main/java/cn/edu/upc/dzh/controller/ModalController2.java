package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.ModalService2;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Modal;
import cn.edu.upc.manage.model.ProjectPlan;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 董志涵
 */
@CrossOrigin
@Controller
@RequestMapping(value="/modal",method={RequestMethod.POST, RequestMethod.GET})
public class ModalController2 {
    @Autowired
    private ModalService2 modalService2;

    /**
     * 审核
     * @param modal
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public CommonReturnType updateState(@RequestBody Modal modal){
        modalService2.updateState(modal);
        return  CommonReturnType.create("审核成功");
    }

    /**
     * 获取全部竣工
     * @param
     * @return
     */
    @RequestMapping("/getAllModal")
    @ResponseBody
    public CommonReturnType getAllModal(){

        return  CommonReturnType.create(modalService2.getAllModal());
    }

    @RequestMapping("/getModalByContractId")
    @ResponseBody
    public CommonReturnType getModalById(@RequestBody JSONObject jsonObject){
        int contractId=jsonObject.getInteger("contractId");
        return  CommonReturnType.create(modalService2.getModalByContractId(contractId));
    }
}
