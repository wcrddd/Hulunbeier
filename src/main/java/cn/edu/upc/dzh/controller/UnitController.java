package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.UnitService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ConstructionUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/unit",method = {RequestMethod.POST,RequestMethod.GET})
public class UnitController {
    @Autowired
    private UnitService unitService;

    /**
     * 获取全部的建设单位
     */
    @RequestMapping("/getUnit")
    @ResponseBody
    public CommonReturnType getUnit(){
        return CommonReturnType.create(unitService.getConstructionUnit());
    }

    /**
     * 通过id获取建设单位信息
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUnitById(@RequestParam("id") Integer id){
        ConstructionUnit constructionUnit = unitService.selectUnitById(id);
        return CommonReturnType.create(constructionUnit,"查询完成");
    }

    @RequestMapping("/addUnit")
    @ResponseBody
    public CommonReturnType addUnit(@RequestBody ConstructionUnit constructionUnit){
        unitService.addUnit(constructionUnit);
        return CommonReturnType.create(null,"新增完成");
    }

    /**
     * 更新建设单位信息
     * @param constructionUnit
     * @return
     */
    @RequestMapping("/updateUnit")
    @ResponseBody
    public CommonReturnType updateUnit(@RequestBody ConstructionUnit constructionUnit){
        unitService.updateUnit(constructionUnit);
        return CommonReturnType.create(null,"修改完成");
    }

    /**
     * 删除建设单位
     * @param id
     * @return
     */
    @RequestMapping("/delUnit")
    @ResponseBody
    public CommonReturnType delUnit(@RequestParam(value = "id")Integer id){
        unitService.delUnit(id);
        return CommonReturnType.create(null,"删除成功");
    }
}