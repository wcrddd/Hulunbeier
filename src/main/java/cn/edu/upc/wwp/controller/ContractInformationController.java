package cn.edu.upc.wwp.controller;

import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.ContractInformation;
import cn.edu.upc.manage.model.ContractInformationWithTenderId;
import cn.edu.upc.manage.model.ProjectSection;
import cn.edu.upc.wwp.service.ContractInformationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/contractInformation",method={RequestMethod.POST, RequestMethod.GET})
public class ContractInformationController {
    @Autowired
    ContractInformationService contractInformationService;

    /**
     * 修改合同（改2020-12-01）
     * @param projectSectionList
     * @return
     */
    @RequestMapping("/updateContractInformation")
    @ResponseBody
    public CommonReturnType update(@RequestBody List<ProjectSection> projectSectionList){

        contractInformationService.updateContractInformation(projectSectionList);

        return CommonReturnType.create(null,"更新成功");
    }

    /**
     * 上传合同（改2020-11-30）
     * @param projectSectionList
     * @return
     */
    @RequestMapping("/insertContractInformation")
    @ResponseBody
    public CommonReturnType insert(@RequestBody List<ProjectSection> projectSectionList){
        contractInformationService.insertContractInformation(projectSectionList);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/deleteContractInformation")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody ContractInformation recordDel){
        contractInformationService.deleteFlag(recordDel);
        return CommonReturnType.create(null,null,0,"删除成功");
    }

    @RequestMapping("/getAllContractInformation")

    @ResponseBody
    public CommonReturnType getAllContractInformation(){
        return CommonReturnType.create(contractInformationService.getAllContractInformation());
    }

//    @RequestMapping("/insertNewContract")
//    @ResponseBody
//    public CommonReturnType insertNewContract(@RequestBody ContractInformation recordIn){
//        contractInformationService.insertContractInformation(recordIn);
//        return CommonReturnType.create();
//    }

    /**
     * 按照项目id获取合同（改2020-12-01）
     * @param projectId
     * @return
     */
    @RequestMapping("/getContractByProjectId")
    @ResponseBody
    public CommonReturnType getContractByProjectId(@RequestParam("projectId") int projectId){
        return CommonReturnType.create(contractInformationService.getContractByProjectId(projectId));
    }

    @RequestMapping("/getContractBytId")
    @ResponseBody
    public CommonReturnType getContractBytId(@RequestBody JSONObject jsonObject){
        int id=jsonObject.getInteger("id");
        return CommonReturnType.create(contractInformationService.getContractBytId(id));
    }

    @RequestMapping("/getContractStatistics")
    @ResponseBody
    public CommonReturnType getContractStatistics(@RequestBody JSONObject jsonObject){
        int projectId=jsonObject.getInteger("projectId");
        return CommonReturnType.create(contractInformationService.getContractStatistics(projectId));
    }

    @RequestMapping("/getContractByTenderId")
    @ResponseBody
    public CommonReturnType getContractByTenderId(@RequestBody JSONObject jsonObject){
        int tenderId=jsonObject.getInteger("tenderId");
        return CommonReturnType.create(contractInformationService.getContractByTenderId(tenderId));
    }

    /**
     * 审批（改2020-12-01）
     * @param projectSection
     * @return
     */
    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody  ProjectSection projectSection){
        contractInformationService.updateApprove(projectSection);
        return CommonReturnType.create(null,"审核成功");
    }

    @RequestMapping("/getAllContractWithProjectName")
    @ResponseBody
    public CommonReturnType getAllContractWithProjectName(){
        return CommonReturnType.create(contractInformationService.getAllContractWithProjectName());
    }

    /**
     *获取本单位可以竣工的项目合同
     * @return
     */
    @RequestMapping("/getCompletedByUnitId")
    @ResponseBody
    public CommonReturnType getCompletedByUnitId(HttpSession session){
                int unitId= SysUser.getCurrentUserUnitId(session);
//        int unitId=1;
        return CommonReturnType.create(contractInformationService.getCompletedByUnitId(unitId));
    }

    /**
     * 获取本单位可以填报施工进程的项目合同
     * @param session
     * @return
     */
    @RequestMapping("/getCanProgress")
    @ResponseBody
    public CommonReturnType getCanProgress(HttpSession session,@RequestBody JSONObject jsonObject){
        String name=jsonObject.getString("projectName");
                int unitId= SysUser.getCurrentUserUnitId(session);
//        int unitId=1;
        return CommonReturnType.create(contractInformationService.selectCanProgress(unitId,name));
    }

    /**
     * 追加合同
     * @param projectSection
     * @return
     */
    @RequestMapping("/continueInsertContractInformation")
    @ResponseBody
    public CommonReturnType continueInsertContractInformation(@RequestBody ProjectSection projectSection){
        contractInformationService.continueInsertContractInformation(projectSection);
        return CommonReturnType.create(null);
    }


}
