package cn.edu.upc.gsl.controller;

import cn.edu.upc.gsl.service.ConstructionProgressService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.mo.ConstructionProgressMo;
import cn.edu.upc.manage.mo.SectionProgressMo;
import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.vo.ConstructionProgressParamVo;
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

    /**
     * 上传施工进程
     * @param constructionProgressParamVo
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public CommonReturnType add(@RequestBody ConstructionProgressParamVo constructionProgressParamVo) {
        constructionProgressService.insert(constructionProgressParamVo);
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
//        Integer contractId =constructionProgress.getContractId();
        Integer contractId = 0;
        List<ConstructionProgress> constructionProgressList= constructionProgressService.select(projectId,contractId);
        return CommonReturnType.create(constructionProgressList, "正常");
    }

    @RequestMapping(value = "/selectArray")
    @ResponseBody
    public CommonReturnType selectArray(@RequestBody ConstructionProgress constructionProgress) {
        Integer projectId = constructionProgress.getProjectId();
//        Integer contractId =constructionProgress.getContractId();
        Integer contractId = 0;
        ConstructionProgressVo progressVo= constructionProgressService.selectArray(projectId,contractId);
        return CommonReturnType.create(progressVo, "正常");
    }

    @RequestMapping(value = "/getByProjectId")
    @ResponseBody
    public CommonReturnType getByProjectId(@RequestParam("projectId") int projectId) {
        List<SectionProgressMo> sectionProgressMoList = constructionProgressService.getByProjectId(projectId);
        return CommonReturnType.create(sectionProgressMoList);
    }

}
