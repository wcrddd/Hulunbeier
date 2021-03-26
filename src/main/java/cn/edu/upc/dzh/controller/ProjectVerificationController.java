package cn.edu.upc.dzh.controller;

import cn.edu.upc.dzh.service.ProjectVerificationService;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.*;
import cn.edu.upc.manage.vo.ProjectListVo;
import cn.edu.upc.manage.vo.ProjectVerificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dzh 2021-3-19
 */
@CrossOrigin
@Controller
@RequestMapping(value="/verification",method = {RequestMethod.POST,RequestMethod.GET})
public class ProjectVerificationController {
    @Autowired
    private ProjectVerificationService projectVerificationService;

//    /**
//     * 新增项目的审价
//     * @param projectVerificationVo
//     * @return
//     */
//    @RequestMapping("/addVerification")
//    @ResponseBody
//    public CommonReturnType addVerification(@RequestBody ProjectVerificationVo projectVerificationVo){
//        projectVerificationService.addVerification(projectVerificationVo);
//        return  CommonReturnType.create("新增成功");
//    }
//
//    /**
//     * 获取项目的审价
//     * @param projectId
//     * @return
//     */
//    @RequestMapping("/getVerification")
//    @ResponseBody
//    public CommonReturnType getVerification(@RequestParam("projectId") int projectId){
//        List<ProjectVerification> projectVerificationList = projectVerificationService.getVerification(projectId);
//        return  CommonReturnType.create(projectVerificationList);
//    }
//
//    /**
//     * 更新项目的审价
//     * @param projectVerificationVo
//     * @return
//     */
//    @RequestMapping("/updateVerification")
//    @ResponseBody
//    public CommonReturnType updateVerification(@RequestBody ProjectVerificationVo projectVerificationVo){
//        projectVerificationService.updateVerification(projectVerificationVo);
//        return  CommonReturnType.create("更新成功");
//    }
//
//    /**
//     * 审批项目的审价
//     * @param projectStore
//     * @return
//     */
//    @RequestMapping("/updateVerificationApprove")
//    @ResponseBody
//    public CommonReturnType updateVerificationApprove(@RequestBody ProjectStore projectStore){
//        projectVerificationService.updateVerificationApprove(projectStore);
//        return  CommonReturnType.create("审批成功");
//    }
    /**
     * 新增项目的审价
     * @param projectList
     * @return
     */
    @RequestMapping("/addVerification")
    @ResponseBody
    public CommonReturnType addVerification(@RequestBody ProjectListVo projectList){
        projectVerificationService.addVerification(projectList);
        return  CommonReturnType.create("新增成功");
    }

    /**
     * 根据id获取项目的审价
     * @param id
     * @return
     */
    @RequestMapping("/getVerification")
    @ResponseBody
    public CommonReturnType getVerification(@RequestParam("id") int id){
        ProjectListVo projectListVo = projectVerificationService.getVerification(id);
        return  CommonReturnType.create(projectListVo);
    }

    /**
     * 更新项目的审价
     * @param projectList
     * @return
     */
    @RequestMapping("/updateVerification")
    @ResponseBody
    public CommonReturnType updateVerification(@RequestBody ProjectListVo projectList){
        projectVerificationService.updateVerification(projectList);
        return  CommonReturnType.create("更新成功");
    }

    /**
     * 审批项目的审价
     * @param projectList
     * @return
     */
    @RequestMapping("/updateVerificationApprove")
    @ResponseBody
    public CommonReturnType updateVerificationApprove(@RequestBody ProjectList projectList){
        projectVerificationService.updateVerificationApprove(projectList);
        return  CommonReturnType.create("审批成功");
    }

    /**
     * 获取全部项目的审价
     * @param httpServletRequest
     * @param httpSession
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public CommonReturnType getALl(HttpServletRequest httpServletRequest, HttpSession httpSession){
        User user = SysUser.getCurrentUser(httpSession,httpServletRequest);
        List<ProjectList> projectListList = projectVerificationService.getAll(user);
        return  CommonReturnType.create(projectListList);
    }

    /**
     * 根据id删除项目的审价
     * @param id
     * @return
     */
    @RequestMapping("/deleteVerification")
    @ResponseBody
    public CommonReturnType deleteVerification(@RequestParam("id") int id){
        projectVerificationService.deleteVerification(id);
        return  CommonReturnType.create("删除成功");
    }


}
