package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.User;
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
        userService.updateUserPassword(recordPassword);
        return  CommonReturnType.create(null);
    }

}
