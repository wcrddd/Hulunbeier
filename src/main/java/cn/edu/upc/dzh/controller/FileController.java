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
//    public static String saveUrl ="/home/pm-application/tomcat/webapps/hl/";
//public static String saveUrl = "/root/tomcat90/webapps/upload2/";
public static String saveUrl = GetIp.saveUrl;
    @RequestMapping("/uploadFile")
    @ResponseBody
    public CommonReturnType uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String type="guide";
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

    @RequestMapping("/uploadStartReport")
    @ResponseBody
    public CommonReturnType uploadProjectPlan(@RequestParam("file") MultipartFile file) throws IOException {
        String type="startReport";
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

    @RequestMapping("/uploadStudyReport")
    @ResponseBody
    public CommonReturnType uploadFeasibility(@RequestParam("file") MultipartFile file) throws IOException {
        String type="studyReport";
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
    public CommonReturnType uploadModal( MultipartFile file) throws IOException {
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

    @RequestMapping("/uploadTender")
    @ResponseBody
    public CommonReturnType uploadTender(@RequestParam("file") MultipartFile file) throws IOException {
        String type="tender";
        return CommonReturnType.create(upload(file,type));
    }

    @RequestMapping("/uploadContract")
    @ResponseBody
    public CommonReturnType uploadContract(@RequestParam("file") MultipartFile file) throws IOException {
        String type="contract";
        return CommonReturnType.create(upload(file,type));
    }

    @RequestMapping("/uploadKeepRecord")
    @ResponseBody
    public CommonReturnType uploadKeepRecord(@RequestParam("file") MultipartFile file) throws IOException {
        String type="keepRecord";
        return CommonReturnType.create(upload(file,type));
    }

    @RequestMapping("/uploadProgress")
    @ResponseBody
    public CommonReturnType uploadProgress(@RequestParam("file") MultipartFile file) throws IOException {
        String type="progress";
        return CommonReturnType.create(upload(file,type));
    }

    @RequestMapping("/uploadSuggestion")
    @ResponseBody
    public CommonReturnType uploadSuggestion(@RequestParam("file") MultipartFile file) throws IOException {
        String type="progress";
        return CommonReturnType.create(upload(file,type));
    }

    public JSONObject upload(@RequestParam("file") MultipartFile file,String type) throws IOException {
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
        return jsonObject;
    }

}
