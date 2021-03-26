package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ProjectList;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.ProjectVerification;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.ProjectListVo;
import cn.edu.upc.manage.vo.ProjectVerificationVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dzh 2021-3-19
 */
public interface ProjectVerificationService {
    /**
     * 新增项目的审价
     * @param projectList
     * @return
     */
    void addVerification(ProjectListVo projectList);

    /**
     * 获取项目的审价
     * @param projectId
     * @return
     */
    ProjectListVo getVerification(int projectId);

    /**
     * 更新项目的审价
     * @param projectList
     * @return
     */
    void updateVerification(ProjectListVo projectList);

    /**
     * 审批项目的审价
     * @param projectList
     * @return
     */
    void updateVerificationApprove(ProjectList projectList);

    /**
     * 获取全部审价
     * @param user
     * @return
     */
    List<ProjectList> getAll(User user);

    /**
     * 根据id删除项目的审价
     * @param id
     * @return
     */
    void deleteVerification(int id);

}
