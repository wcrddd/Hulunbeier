package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectType;
import cn.edu.upc.wwp.service.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/projectType",method={RequestMethod.POST, RequestMethod.GET})
public class ProjectTypeController {

    @Autowired
    ProjectTypeService projectTypeService;
    @RequestMapping("/selectProjectType")

    @ResponseBody
    public CommonReturnType select(){


        List<ProjectType> list1= projectTypeService.selectProjectType();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/updateProjectType")

    @ResponseBody
    public CommonReturnType update(@RequestBody ProjectType recordUp){

        projectTypeService.updateProjectType(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertProjectType")

    @ResponseBody
    public CommonReturnType insert(@RequestBody ProjectType recordIn){
        projectTypeService.insertProjectType(recordIn);
        List<ProjectType> list1= projectTypeService.selectProjectType();
        String msg;
        return  CommonReturnType.create(list1,"null");
    }

    @RequestMapping("/deleteProjectType")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody ProjectType recordDel){
        projectTypeService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }
}
