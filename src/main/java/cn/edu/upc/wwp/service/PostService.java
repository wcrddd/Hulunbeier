package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.Post;

import java.util.List;

public interface PostService {
    public List<Post> selectPost();
    public void updatePost(Post recordUp);
    public void insertPost(Post recordIn);
    public void deleteFlag(Post recordDel);

}
