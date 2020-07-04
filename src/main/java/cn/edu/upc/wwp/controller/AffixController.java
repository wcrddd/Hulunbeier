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

    @RequestMapping("/updateAffix")

    @ResponseBody
    public CommonReturnType update(@RequestBody Affix recordUp){

        affixService.updateAffix(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertAffix")

    @ResponseBody
    public CommonReturnType insert(@RequestBody JSONObject jsonObject){

        int projectId=jsonObject.getInteger("projectId");
        int contractId=jsonObject.getInteger("contractId");
        JSONArray name=jsonObject.getJSONArray("affixName");
        JSONArray path=jsonObject.getJSONArray("affixPath");
        Affix affix=new Affix();
        affix.setProjectId(projectId);
        affix.setContractId(contractId);
        for(int i=0;i<name.size();i++){
            affix.setAffixName(name.getString(i));
            affix.setAffixPath(path.getString(i));
            affixService.insertAffix(affix);
        }

        return  CommonReturnType.create("新增成功");
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
        affix.setContractId(contractId);
        for(int i=0;i<fileArray.size();i++){
            JSONObject jsonObject1=fileArray.getJSONObject(i);
            affix.setAffixName(jsonObject1.getString("fileName"));
            affix.setAffixPath(jsonObject1.getString("path"));
            affixService.insertAffix(affix);
        }

        return  CommonReturnType.create("新增成功");
    }
}
