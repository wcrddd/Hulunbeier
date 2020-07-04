package cn.edu.upc.wwp.controller;


import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Modal;
import cn.edu.upc.wwp.service.ModalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/modal",method={RequestMethod.POST, RequestMethod.GET})
public class ModalController {

    @Autowired
    ModalService modalService;

    @RequestMapping("/selectModal")

    @ResponseBody
    public CommonReturnType select(){


        List< Modal> list1= modalService.selectModal();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/updateModal")

    @ResponseBody
    public CommonReturnType update(@RequestBody  Modal recordUp){

        modalService.updateModal(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertModal")

    @ResponseBody
    public CommonReturnType insert(@RequestBody  Modal recordIn){
        modalService.insertModal(recordIn);
        List< Modal> list1= modalService.selectModal();
        String msg;
        return  CommonReturnType.create(list1,"null");
    }

    @RequestMapping("/deleteModal")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody Modal recordDel){
        modalService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }


}
