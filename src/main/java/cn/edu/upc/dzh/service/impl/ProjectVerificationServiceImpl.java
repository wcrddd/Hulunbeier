package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.ProjectVerificationService;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.dao.ProjectListMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.ProjectVerificationMapper;
import cn.edu.upc.manage.model.ProjectList;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.ProjectVerification;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.ProjectListVo;
import cn.edu.upc.manage.vo.ProjectVerificationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzh 2021-3-19
 */
@Service
public class ProjectVerificationServiceImpl implements ProjectVerificationService {
    @Autowired
    private ProjectVerificationMapper projectVerificationMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;
    @Autowired
    private ProjectListMapper projectListMapper;

//    /**
//     * 新增项目的审价
//     * @param projectVerificationVo
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void addVerification(ProjectVerificationVo projectVerificationVo){
//        List<ProjectVerification> projectVerificationList = projectVerificationVo.getProjectVerificationList();
//        projectVerificationMapper.insertVerificationList(projectVerificationList);
//        ProjectStore projectStore = new ProjectStore();
//        projectStore.setId(projectVerificationList.get(0).getProjectId());
//        projectStore.setApprove(1);
//        projectStoreMapper.updateVerificationApprove(projectStore);
//    }
//
//    /**
//     * 获取项目的审价
//     * @param projectId
//     * @return
//     */
//    @Override
//    public List<ProjectVerification> getVerification(int projectId){
//        return projectVerificationMapper.getVerification(projectId);
//    }
//
//    /**
//     * 更新项目的审价
//     * @param projectVerificationVo
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateVerification(ProjectVerificationVo projectVerificationVo){
//        List<ProjectVerification> projectVerificationList = projectVerificationVo.getProjectVerificationList();
//        projectVerificationMapper.updateVerificationList(projectVerificationList);
//        ProjectStore projectStore = new ProjectStore();
//        projectStore.setId(projectVerificationVo.getProjectId());
//        projectStore.setApprove(1);
//        projectStoreMapper.updateVerificationApprove(projectStore);
//    }
//
//    /**
//     * 审批项目的审价
//     * @param projectStore
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateVerificationApprove(ProjectStore projectStore){
//        projectStoreMapper.updateVerificationApprove(projectStore);
//    }

    /**
     * 新增项目的审价
     * @param projectList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addVerification(ProjectListVo projectList){
        String list = "";
        for (String item:projectList.getFileList()
             ) {
//            list = list + "," + item;
            list = item + "," + list;
        }
        projectList.setList(list);
        projectListMapper.insertSelective(projectList);
    }

    /**
     * 获取项目的审价
     * @param id
     * @return
     */
    @Override
    public ProjectListVo getVerification(int id){
        ProjectList projectList = projectListMapper.selectByPrimaryKey(id);
        ProjectListVo projectListVo = new ProjectListVo();
        BeanUtils.copyProperties(projectList,projectListVo);
        String list = projectList.getList();
        String[] fileList = list.split(",");
        List<String> fileLists = new ArrayList<>();
        for (String item:fileList
             ) {
            System.out.println(item+",");
            fileLists.add(item);
        }
        for (String i:fileLists
             ) {
            System.out.println(i+",");
        }
        projectListVo.setFileList(fileLists);
        return projectListVo;
    }

    /**
     * 更新项目的审价
     * @param projectList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateVerification(ProjectListVo projectList){
        String list = "";
        for (String item:projectList.getFileList()
        ) {
            list = item + "," + list;
        }
        projectList.setList(list);
        projectList.setApprove(1);
        projectListMapper.updateByPrimaryKeySelective(projectList);
    }

    /**
     * 审批项目的审价
     * @param projectList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateVerificationApprove(ProjectList projectList){
        projectListMapper.updateByPrimaryKeySelective(projectList);
    }

    /**
     * 获取全部审价
     * @param user
     * @return
     */
    @Override
    public List<ProjectList> getAll(User user){
        return projectListMapper.getAll(user);
    }

    /**
     * 根据id删除项目的审价
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteVerification(int id){
        projectListMapper.deleteVerification(id);
    }
}
