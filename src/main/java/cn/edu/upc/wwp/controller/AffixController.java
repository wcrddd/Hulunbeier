package cn.edu.upc.wwp.controller;


import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Affix;
import cn.edu.upc.wwp.service.AffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/affix",method={RequestMethod.POST, RequestMethod.GET})
public class AffixController {
    @Autowired
    AffixService affixService;

    @RequestMapping("/selectAffix")

    @ResponseBody
    public CommonReturnType select(){


        List<Affix> list1= affixService.selectAffix();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/updateAffix")

    @ResponseBody
    public CommonReturnType update(@RequestBody Affix recordUp){

        affixService.updateAffix(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertAffix")

    @ResponseBody
    public CommonReturnType insert(@RequestBody Affix recordIn){
        affixService.insertAffix(recordIn);
        List<Affix> list1= affixService.selectAffix();
        String msg;
        return  CommonReturnType.create(list1,"null");
    }

    @RequestMapping("/deleteAffix")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody Affix recordDel){
        affixService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }
}
