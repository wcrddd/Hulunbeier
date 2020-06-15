package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.TenderInformationService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.TenderInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
