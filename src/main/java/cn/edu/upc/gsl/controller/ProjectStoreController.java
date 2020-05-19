package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.ProjectStoreService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author gsl
 * @date 2020/5/16
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/project",method = {RequestMethod.GET,RequestMethod.POST})
public class ProjectStoreController {


    @Autowired
    ProjectStoreService projectStoreService;

    /**
     * 项目申报（建设单位）
     * @param projectStore 项目储备库
     * @return
     */
    @RequestMapping(value = "/addProject")
    @ResponseBody
    public CommonReturnType addProject(@RequestBody ProjectStore projectStore){
        projectStoreService.addProject(projectStore);
        return CommonReturnType.create(null,"项目申报成功，等待审批");
    }
}
