package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.ConstructionProgressService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.vo.ConstructionProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>manage</h3>
 * <p>施工进度信息</p>
 *
 * @author : gsl
 * @date : 2020-06-30 15:31
 * CrossOrigin解决跨域问题，此处设定时间3小时
 * 3*60*60 = 10800
 **/

@Controller
@CrossOrigin(origins = "*", maxAge = 10800)
@RequestMapping(value = "/progress",method = {RequestMethod.POST,RequestMethod.GET})
public class ConstructionProgressController {


    @Autowired
    ConstructionProgressService constructionProgressService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public CommonReturnType add(@RequestBody ConstructionProgress constructionProgress) {
        constructionProgressService.insert(constructionProgress);
        return CommonReturnType.create(null, "正常");
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public CommonReturnType update(@RequestBody ConstructionProgress constructionProgress) {
        constructionProgressService.update(constructionProgress);
        return CommonReturnType.create(null, "正常");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public CommonReturnType delete(@RequestBody ConstructionProgress constructionProgress) {
        Integer id = constructionProgress.getId();
        constructionProgressService.deleteById(id);
        return CommonReturnType.create(null, "正常");
    }

    @RequestMapping(value = "/select")
    @ResponseBody
    public CommonReturnType select(@RequestBody ConstructionProgress constructionProgress) {
        Integer projectId = constructionProgress.getProjectId();
        Integer contractId =constructionProgress.getContractId();
        List<ConstructionProgress> constructionProgressList= constructionProgressService.select(projectId,contractId);
        return CommonReturnType.create(constructionProgressList, "正常");
    }

    @RequestMapping(value = "/selectArray")
    @ResponseBody
    public CommonReturnType selectArray(@RequestBody ConstructionProgress constructionProgress) {
        Integer projectId = constructionProgress.getProjectId();
        Integer contractId =constructionProgress.getContractId();
        ConstructionProgressVo progressVo= constructionProgressService.selectArray(projectId,contractId);
        return CommonReturnType.create(progressVo, "正常");
    }

}
