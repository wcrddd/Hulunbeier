package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.RoleService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.RightRole;
import cn.edu.upc.manage.model.Role;
import com.alibaba.fastjson.JSONArray;
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
        roleService.deleteRightRole(roleId);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

    @RequestMapping("/insertNewRole")
    @ResponseBody
    public CommonReturnType insertNewRole(@RequestBody JSONObject para){
        String roleName=para.getString("roleName");
        JSONArray rightsList = para.getJSONArray("rightsList");
        Role role =new Role();
        role.setRoleName(roleName);
        int roleId=roleService.insertNewRole(role);
        for(int i=0;i<rightsList.size();i++){
            int rightId=rightsList.getInteger(i);
            RightRole rightRole =new RightRole();
            rightRole.setRightId(rightId);
            rightRole.setRoleId(roleId);
            roleService.insertNewRightRole(rightRole);
        }
        return CommonReturnType.create(null,null,0,"新增成功");
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public CommonReturnType updateRole(@RequestBody JSONObject para){
        int roleId=para.getInteger("roleId");
        roleService.deleteRightRole(roleId);
        JSONArray rightsList = para.getJSONArray("rightsList");
        String roleName=para.getString("roleName");
//        Role role=new Role();
//        role.setId(roleId);
//        role.setRoleName(roleName);
//        roleService.updatetRole(role);
        roleService.updatetRoleName(roleId,roleName);
        int length=rightsList.size();
        for(int b=0;b<length;b++)
        {
            int rightId=rightsList.getInteger(b);
            RightRole rightRole =new RightRole();
            rightRole.setRightId(rightId);
            rightRole.setRoleId(roleId);
            roleService.insertNewRightRole(rightRole);
        }
        return CommonReturnType.create(null,null,0,"修改成功");
    }
}
