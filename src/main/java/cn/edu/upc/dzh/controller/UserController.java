package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.dzh.until.MD5Util;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.model.UserWithUnitName;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 董志涵
 */
@CrossOrigin
@Controller
@RequestMapping(value="/user",method={RequestMethod.POST, RequestMethod.GET})
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/updatePassword")
    @ResponseBody
    public CommonReturnType updatePassword(@RequestBody User recordPassword){
        recordPassword.setPassword(MD5Util.md5(recordPassword.getPassword()));
        userService.updateUserPassword(recordPassword);
        return CommonReturnType.create(null,null,0,"更新成功");
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public CommonReturnType updateUser(@RequestBody User recordPassword){
        userService.updateUserPassword(recordPassword);
        return CommonReturnType.create(null,null,0,"更新成功");
    }

//    @RequestMapping("/updatePassword")
//    @ResponseBody
//    public CommonReturnType updatePassword(@RequestBody User recordPassword){
//        recordPassword.setPassword(MD5Util.md5(recordPassword.getPassword()));
//        userService.updateUserPassword(recordPassword);
//        return CommonReturnType.create(null,null,0,"更新成功");
//    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public CommonReturnType deleteUser(@RequestBody JSONObject jsonObject){
        int userId=jsonObject.getInteger("userId");
        System.out.println(userId);
        userService.deleteUser(userId);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public CommonReturnType getAllUser(){
        return CommonReturnType.create(userService.getAllUser());
    }

    @RequestMapping("/selectByUsername")
    @ResponseBody
    public CommonReturnType selectByUsername(@RequestBody JSONObject jsonObject){
        String username=jsonObject.getString("username");
        return CommonReturnType.create(userService.selectByUsername(username));
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public CommonReturnType changePassword(@RequestBody JSONObject jsonObject, HttpSession session){
        String oldPassword=jsonObject.getString("oldPassword");
        String newPassword=jsonObject.getString("newPassword");
        User user=userService.getByUsername(SysUser.getUsername(session));
        if(MD5Util.md5(oldPassword).equals(user.getPassword())){
            userService.changePasswordByUsername(MD5Util.md5(newPassword),user.getUserName());
        }else{
            throw new BusinessException(EmBusinessError.PASSWORD_ERROR);
        }
        return CommonReturnType.create(null,null,0,"更新成功");
    }

}
