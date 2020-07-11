package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.RightService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Rights;
import cn.edu.upc.manage.model.ViewRights;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/right",method = {RequestMethod.POST,RequestMethod.GET})
public class RightController {
    @Autowired
    private RightService rightService;


    @RequestMapping("/insertNewRight")//插入一条
    @ResponseBody
    public CommonReturnType insertNewRight(){
        Rights r1=new Rights();
        r1.setLastId(0);
        r1.setDelFlag(0);
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
    public CommonReturnType updateRight(@RequestBody Rights right){
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

    @RequestMapping("/selectRightByRole")
    @ResponseBody
    public CommonReturnType selectRightByRole(HttpSession session){
        List<ViewRights> p1 = rightService.selectRightByRole(SysUser.getCurrentUserRole(session));
//        List<ViewRights> p1 = rightService.selectRightByRole(15);
        return CommonReturnType.create(p1);
    }

    @RequestMapping("/selectByName")//
    @ResponseBody
    public CommonReturnType selectByName(@RequestBody JSONObject jsonObject){
        String name=jsonObject.getString("rightName");
        return CommonReturnType.create(rightService.selectByName(name));
    }

    @RequestMapping("/insertRight")//插入一条
    @ResponseBody
    public CommonReturnType insertRight(@RequestBody Rights right){
        rightService.insertRight2(right);
        return CommonReturnType.create(null,null,0,"新增成功");
    }

}
