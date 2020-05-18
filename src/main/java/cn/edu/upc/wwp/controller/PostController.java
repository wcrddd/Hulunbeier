package cn.edu.upc.wwp.controller;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.model.Post;
import cn.edu.upc.wwp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/post",method={RequestMethod.POST, RequestMethod.GET})
public class PostController {
    @Autowired
    PostService postService;
    @RequestMapping("/selectPost")

    @ResponseBody
    public CommonReturnType select(){


        List<Post> list1= postService.selectPost();

        return  CommonReturnType.create(list1,"查询成功");
    }

    @RequestMapping("/updatePost")

    @ResponseBody
    public CommonReturnType update(@RequestBody Post recordUp){

        postService.updatePost(recordUp);

        return  CommonReturnType.create(null);
    }

    @RequestMapping("/insertPost")

    @ResponseBody
    public CommonReturnType insert(@RequestBody Post recordIn){
        postService.insertPost(recordIn);
        List<Post> list1= postService.selectPost();
        String msg;
        return  CommonReturnType.create(list1,"null");
    }

    @RequestMapping("/deletePost")

    @ResponseBody
    public CommonReturnType deleteFlag(@RequestBody Post recordDel){
        postService.deleteFlag(recordDel);
        return  CommonReturnType.create("null");
    }

}
