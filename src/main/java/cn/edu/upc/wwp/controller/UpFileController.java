package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping(value="/upFile",method = {RequestMethod.POST,RequestMethod.GET})
public class UpFileController {
    //服务器的根目录
    public static String saveUrl ="/root/apache-tomcat-9.0.31/webapps/hl/";
    @RequestMapping("/uploadFile")
    @ResponseBody
    public CommonReturnType uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String type="affix";
        //获取系统当前的时间，按照时间分类创建文件夹
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();

        System.out.println("源文件名："+fileName);
        File filed=new File(saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        //时间+随机数+后缀名重新命名防止重名
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        //把文件上传到指定文件夹下面
       file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
    }



}
