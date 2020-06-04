package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.dao.ConstructionMapper;
import cn.edu.upc.manage.model.Construction;
import cn.edu.upc.wwp.service.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/construction",method={RequestMethod.POST, RequestMethod.GET})
public class ConstructionController {
    @Autowired
    ConstructionService constructionService;
    @RequestMapping("/selectConstruction")

    @ResponseBody
    public CommonReturnType select(){


        List<Construction> list1= constructionService.selectConstruction();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/updateProjectType")

    @ResponseBody
    public CommonReturnType update(@RequestBody Construction recordUp){

        constructionService.updateConstruction(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertProjectType")

    @ResponseBody
    public CommonReturnType insert(@RequestBody Construction recordIn){
        constructionService.insertConstruction(recordIn);
        List<Construction> list1= constructionService.selectConstruction();
        String msg;
        return  CommonReturnType.create(list1,"null");
    }

    @RequestMapping("/deleteProjectType")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody Construction recordDel){
        constructionService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }


}
