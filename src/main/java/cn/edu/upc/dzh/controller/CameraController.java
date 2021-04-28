package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.CameraService;
import cn.edu.upc.dzh.until.MD5Util;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Camera;
import cn.edu.upc.manage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 董志涵
 */
@CrossOrigin
@Controller
@RequestMapping(value="/camera",method={RequestMethod.POST, RequestMethod.GET})
public class CameraController {
    @Autowired
    private CameraService cameraService;

    /**
     * 新增摄像头
     * @param camera
     * @return
     */
    @RequestMapping("/insertCamera")
    @ResponseBody
    public CommonReturnType insertCamera(@RequestBody Camera camera){
        cameraService.insertCamera(camera);
        return CommonReturnType.create(null,null,0,"新增成功");
    }

    /**
     * 删除摄像头
     * @param id
     * @return
     */
    @RequestMapping("/deleteCamera")
    @ResponseBody
    public CommonReturnType deleteCamera(int id){
        cameraService.deleteCamera(id);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

    /**
     * 修改摄像头
     * @param camera
     * @return
     */
    @RequestMapping("/updateCamera")
    @ResponseBody
    public CommonReturnType updateCamera(@RequestBody Camera camera){
        cameraService.updateCamera(camera);
        return CommonReturnType.create(null,null,0,"修改成功");
    }

    /**
     * 获取摄像头
     * @param projectId
     * @return
     */
    @RequestMapping("/getCameraByProjectId")
    @ResponseBody
    public CommonReturnType getCameraByProjectId(int projectId){
        return CommonReturnType.create(cameraService.getCameraByProjectId(projectId));
    }
}
