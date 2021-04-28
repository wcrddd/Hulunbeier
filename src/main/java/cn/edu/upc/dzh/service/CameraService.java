package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Camera;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface CameraService {
    /**
     * 新增摄像头
     * @param camera
     * @return
     */
    void insertCamera(Camera camera);

    /**
     * 删除摄像头
     * @param id
     * @return
     */
    void deleteCamera(int id);

    /**
     * 修改摄像头
     * @param camera
     * @return
     */
    void updateCamera(Camera camera);

    /**
     * 获取摄像头
     * @param projectId
     * @return
     */
    List<Camera> getCameraByProjectId(int projectId);
}
