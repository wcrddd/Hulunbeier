package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.CameraService;
import cn.edu.upc.manage.dao.CameraMapper;
import cn.edu.upc.manage.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraMapper cameraMapper;

    /**
     * 新增摄像头
     * @param camera
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCamera(Camera camera){
        cameraMapper.insertSelective(camera);
    }

    /**
     * 删除摄像头
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCamera(int id){
        cameraMapper.deleteCamera(id);
    }

    /**
     * 修改摄像头
     * @param camera
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCamera(Camera camera){
        cameraMapper.updateByPrimaryKeySelective(camera);
    }

    /**
     * 获取摄像头
     * @param projectId
     * @return
     */
    @Override
    public List<Camera> getCameraByProjectId(int projectId){
        return cameraMapper.getCameraByProjectId(projectId);
    }
}
