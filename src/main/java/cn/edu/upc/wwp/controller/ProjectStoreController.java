package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.wwp.service.ProjectStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/projectStore",method={RequestMethod.POST, RequestMethod.GET})
public class ProjectStoreController {
    @Autowired
    public ProjectStoreService projectStoreService;


    @RequestMapping("/getProjectStoreList")

    @ResponseBody
    public CommonReturnType getProjectStoreList(){
        List<ProjectStore> list1= projectStoreService.selectProjectStore();
        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/searchProjectStoreList")

    @ResponseBody
    public CommonReturnType searchProjectStoreList(@RequestBody ProjectStore projectStore){
        List<ProjectStore> list2= projectStoreService.searchProjectStore(projectStore);
        return  CommonReturnType.create(list2,"查询成功");
    }
}
