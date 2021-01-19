package cn.edu.upc.wwp.controller;


import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Affix;
import cn.edu.upc.wwp.service.AffixService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/affix",method={RequestMethod.POST, RequestMethod.GET})
public class AffixController {
    @Autowired
    AffixService affixService;

    @RequestMapping("/selectAffix")

    @ResponseBody
    public CommonReturnType select(){


        List<Affix> list1= affixService.selectAffix();

        return  CommonReturnType.create(list1,"查询成功");
    }

    /**
     * 修改验收报告
     * @param recordUp
     * @return
     */
    @RequestMapping("/updateAffix")
    @ResponseBody
    public CommonReturnType update(@RequestBody Affix recordUp){

        affixService.updateAffix(recordUp);

        return  CommonReturnType.create(null);
    }

    /**
     * 上传验收报告
     * @param affix
     * @return
     */
    @RequestMapping("/insertReport")
    @ResponseBody
    public CommonReturnType insertReport(@RequestBody Affix affix){
            affixService.insertAffix(affix);
        return  CommonReturnType.create("新增成功");
    }

    /**
     * 审批验收报告
     * @param affix
     * @return
     */
    @RequestMapping("/updateApprove")
    @ResponseBody
    public CommonReturnType updateApprove(@RequestBody Affix affix){
        affixService.updateApprove(affix);
        return  CommonReturnType.create("审批成功");
    }

    /**
     * 获取全部验收报告
     * @return
     */
    @RequestMapping("/getAllReport")
    @ResponseBody
    public CommonReturnType getAllReport(){
        List<Affix> affixList = affixService.getAllReport();
        return  CommonReturnType.create(affixList);
    }

    @RequestMapping("/deleteAffix")
    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody Affix recordDel){
        affixService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }

    @RequestMapping("/getAffixByContractId")
    @ResponseBody
    public CommonReturnType getAffixByContractId(@RequestBody JSONObject jsonObject){
        int contractId=jsonObject.getInteger("contractId");
        return  CommonReturnType.create(affixService.getAffixByContractId(contractId));
    }

    @RequestMapping("/insertAffix2")
    @ResponseBody
    public CommonReturnType insertAffix2(@RequestBody JSONObject jsonObject){

        int projectId=jsonObject.getInteger("projectId");
        int contractId=jsonObject.getInteger("contractId");
        JSONArray fileArray=jsonObject.getJSONArray("fileArray");
        Affix affix=new Affix();
        affix.setProjectId(projectId);
//        affix.setContractId(contractId);
        for(int i=0;i<fileArray.size();i++){
            JSONObject jsonObject1=fileArray.getJSONObject(i);
            affix.setAffixName(jsonObject1.getString("fileName"));
            affix.setAffixPath(jsonObject1.getString("path"));
            affixService.insertAffix(affix);
        }

        return  CommonReturnType.create("新增成功");
    }

    /**
     * 根据项目id获取验收报告
     * @param projectId
     * @return
     */
    @RequestMapping("/getByProjectId")
    @ResponseBody
    public CommonReturnType getByProjectId(@RequestParam("projectId") int projectId){
        return  CommonReturnType.create(affixService.getByProjectId(projectId));
    }
}
