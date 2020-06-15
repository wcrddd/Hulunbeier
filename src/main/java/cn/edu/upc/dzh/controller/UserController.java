package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.dzh.until.MD5Util;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value="/user",method={RequestMethod.POST, RequestMethod.GET})
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/updatePassword")
    @ResponseBody
    public CommonReturnType update(@RequestBody User recordPassword){
        recordPassword.setPassword(MD5Util.md5(recordPassword.getPassword()));
        userService.updateUserPassword(recordPassword);
        return CommonReturnType.create(null,null,0,"更新成功");
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public CommonReturnType deleteUser(@RequestBody JSONObject jsonObject){
        int userId=jsonObject.getInteger("id");
        userService.deleteUser(userId);
        return CommonReturnType.create(null,null,0,"更新成功");
    }

}
