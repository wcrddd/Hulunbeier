package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.dzh.service.RightService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Right;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/right",method = {RequestMethod.POST,RequestMethod.GET})
public class RightController {
    @Autowired
    private RightService rightService;


    @RequestMapping("/insertRight")//插入一条
    @ResponseBody
    public CommonReturnType insertRight(){
        Right r1=new Right();
        rightService.insertRight(r1);
        return CommonReturnType.create(rightService.getAllRight());
    }

    @RequestMapping("/getAllRight")//获取全部权限
    @ResponseBody
    public CommonReturnType getAllRight(){
        return CommonReturnType.create(rightService.getAllRight());
    }

    @RequestMapping("/updateRight")//更新权限
    @ResponseBody
    public CommonReturnType updateRight(@RequestBody Right right){
        rightService.updateRight(right);
        return CommonReturnType.create(null,null,0,"更新成功");
    }

    @RequestMapping("/deleteRight")//删除权限
    @ResponseBody
    public CommonReturnType deleteRight(@RequestBody JSONObject jsonObject){
        int rightId=jsonObject.getIntValue("rightId");
        rightService.deleteRight(rightId);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

}
