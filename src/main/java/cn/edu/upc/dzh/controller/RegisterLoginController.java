package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.RegisterLoginService;
import cn.edu.upc.dzh.until.MD5Util;
import cn.edu.upc.dzh.until.SendEmailUtil;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.dzh.until.UUIDUtil;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@Controller
@RequestMapping(value="/web",method = {RequestMethod.POST,RequestMethod.GET})
//@RequestMapping(value="/web")
public class RegisterLoginController {
    @Autowired
    private RegisterLoginService registerLoginService;

    @ResponseBody
    @RequestMapping("/register")
    public CommonReturnType register(@RequestBody User user){
        if(registerLoginService.selectByUsername(user.getUserName())!=null){
            return CommonReturnType.create("用户名已存在");
        }else if(registerLoginService.selectByEmail(user.getEmail())!=null){
            return CommonReturnType.create("邮箱已存在");
        }else{
            user.setPassword(MD5Util.md5(user.getPassword()));
            registerLoginService.insertUser(user);
            return CommonReturnType.create("注册成功");
        }

    }

    @ResponseBody
    @RequestMapping("/selectUsername")
    public CommonReturnType selectUsername(@RequestBody JSONObject username){
        if(registerLoginService.selectByUsername(username.getString("username"))!=null){
            return CommonReturnType.create("用户名已存在");
        }else{
            return CommonReturnType.create("此用户名可用");
        }
    }

    @ResponseBody
    @RequestMapping("/selectEmail")
    public CommonReturnType selectEmail(@RequestBody JSONObject email){
        if(registerLoginService.selectByEmail(email.getString("email"))!=null){
            return CommonReturnType.create("邮箱已注册");
        }else{
            return CommonReturnType.create("此邮箱可注册");
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonReturnType login(@RequestBody JSONObject user, HttpSession session){
        String loginName=user.getString("loginName");
        String password= MD5Util.md5(user.getString("password"));
        //String password=MD5Util.md5(password2);
        System.out.println("1用户名和密码："+loginName+password);
//        Subject subject = SecurityUtils.getSubject();
        System.out.println("2用户名和密码："+loginName+password);
//        UsernamePasswordToken token = new UsernamePasswordToken(loginName,password);
        Map<String,Object> returnMsg = new HashMap<String, Object>();
        User user1 = registerLoginService.selectByUsername(loginName);
        if(user1!=null){
            if(user1.getPassword().equals(password)){
                String token = user1.getId().toString()+'_'+ UUIDUtil.uuid();
                session.setAttribute(token,user1);
                session.setAttribute("user",user1);
                session.setMaxInactiveInterval(120*60);
                System.out.println("sessionId:"+session.getId());
                returnMsg.put("loginTips","登陆成功");
                returnMsg.put("token",token);
                returnMsg.put("role",user1.getRoleId());
//                returnMsg.put("userType",SysUser.getCurrentUserRole(session));
                return CommonReturnType.create(returnMsg);
            }else{
                throw new BusinessException(EmBusinessError.STUDENT_LOGIN_FAIL);
            }

        }else{
            throw new BusinessException(EmBusinessError.STUDENT_NOT_EXIST);
        }


    }

    /**
     * 退出登录
     * 清楚session即可
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public CommonReturnType logout(HttpSession session){
        session.invalidate();
        return CommonReturnType.create("退出成功");
    }

    /**
     * 发送验证码
     * @param jsonObject
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendCode")
    @ResponseBody
    public CommonReturnType sendCode(@RequestBody JSONObject jsonObject,final HttpServletRequest request) throws Exception {
        final String email=jsonObject.getString("email");
//        String realName=jsonObject.getString("realName");
//        User user=userService.selectByEmail(email);
        User user=registerLoginService.selectByEmail(email);

        if(user!=null){
//            if(user.getRealName().equals(realName)){
            String code2=smsCode();
            System.out.println(code2);
            SendEmailUtil.sendEMail(email,code2);

            final HttpSession session = request.getSession();
            session.setAttribute("code",code2);
            return CommonReturnType.create("发送成功");
//            }else {
//                return CommonReturnType.create("真实姓名不正确");
//            }

        }else {
            return CommonReturnType.create("邮箱没有注册");
        }

    }

    /**
     * 匹配验证码并修改密码
     * @param jsonObject
     * @param request
     * @return
     */
    @RequestMapping("/codeMaching")
    @ResponseBody
    public CommonReturnType codeMaching(@RequestBody JSONObject jsonObject, final HttpServletRequest request){
        String email=jsonObject.getString("email");
        String code=jsonObject.getString("code");
        String newPassword= MD5Util.md5(jsonObject.getString("password"));
        HttpSession session=request.getSession();
        String rightCode=session.getAttribute("code").toString();
        System.out.println(code+"   "+rightCode);

        if(rightCode.equals(code)){
            registerLoginService.changePasswordByEmail(newPassword,email);
            return CommonReturnType.create("验证成功");
        }else {
            return CommonReturnType.create("验证失败");
        }


    }

    /**
     * 获取用户名
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserWithUnitName")
    public CommonReturnType getUserWithUnitName(HttpSession session, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        User user = (User) session.getAttribute(token);
        return CommonReturnType.create(registerLoginService.getUserWithUnitName(user));
    }

    /**
     * 创建验证码
     * @return
     */
    public static String smsCode(){
        //创建一个6位的随机数字
        String ran = new Random().nextInt(1000000)+"";
        if(ran.length()!=6){
            smsCode();
        }
        return ran;
    }
}
