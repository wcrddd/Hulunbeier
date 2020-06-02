package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.UnitService;
import cn.edu.upc.manage.common.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/unit",method = {RequestMethod.POST,RequestMethod.GET})
public class UnitController {
    @Autowired
    private UnitService unitService;

    @RequestMapping("/getUnit")//获取全部的建设单位
    @ResponseBody
    public CommonReturnType getUnit(){
        return CommonReturnType.create(unitService.getConstructionUnit());
    }
}