package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.until.GetIp;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.common.CommonReturnType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping(value="/file",method = {RequestMethod.POST,RequestMethod.GET})
public class FileController {
//    public static String saveUrl ="/Users/weixj/Desktop/hlb/";
    public static String saveUrl ="/home/pm-application/tomcat/webapps/hl/";
    @RequestMapping("/uploadFile")
    @ResponseBody
    public CommonReturnType uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String type="guide";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(GetIp.saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
    }

    @RequestMapping("/uploadProjectPlan")
    @ResponseBody
    public CommonReturnType uploadProjectPlan(@RequestParam("file") MultipartFile file) throws IOException {
        String type="projectPlan";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
    }

    @RequestMapping("/uploadFeasibility")
    @ResponseBody
    public CommonReturnType uploadFeasibility(@RequestParam("file") MultipartFile file) throws IOException {
        String type="feasibility";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
    }

    @RequestMapping("/uploadDesign")
    @ResponseBody
    public CommonReturnType uploadDesign(@RequestParam("file") MultipartFile file) throws IOException {
        String type="design";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("path",currentTime+"/"+type+"/"+filename);
        jsonObject.put("fileName",fileName);
//        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(jsonObject);
    }

    @RequestMapping("/uploadModal")
    @ResponseBody
    public CommonReturnType uploadModal(@RequestParam("file") MultipartFile file) throws IOException {
        String type="modal";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("path",currentTime+"/"+type+"/"+filename);
        jsonObject.put("fileName",fileName);
//        return CommonReturnType.create(currentTime+"/"+type+"/"+filename);
        return CommonReturnType.create(jsonObject);
    }

}
