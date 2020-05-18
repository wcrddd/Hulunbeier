package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.PostMapper;
import cn.edu.upc.manage.model.Post;
import cn.edu.upc.wwp.service.PostService;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource
    PostMapper postMapper;

    @Override
    public List<Post> selectPost() {
        return postMapper.selectPost();
    }

    @Override
    public void updatePost(Post recordUp) {
         recordUp.setOperator("test");
         postMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public void insertPost(Post recordIn) {
        recordIn.setOperator("test");
        postMapper.insertSelective(recordIn);
    }

    @Override
    public void deleteFlag(Post recordDel) {

        Post result=postMapper.selectByPrimaryKey(recordDel.getId());
         if(result!=null){
         recordDel.setDelFlag(1);
         postMapper.updateByPrimaryKeySelective(recordDel);
        }
    }
}
