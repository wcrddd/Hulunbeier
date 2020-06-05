package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.RoleService;
import cn.edu.upc.manage.common.CommonReturnType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/role",method = {RequestMethod.POST,RequestMethod.GET})
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getAllRole")//获取全部角色
    @ResponseBody
    public CommonReturnType getAllRole(){
        return CommonReturnType.create(roleService.getAllRole());
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public CommonReturnType deleteRole(@RequestBody JSONObject jsonObject){
        int roleId=jsonObject.getInteger("roleId");
        roleService.deleteRole(roleId);
        return CommonReturnType.create(null,null,0,"删除成功");
    }
}
