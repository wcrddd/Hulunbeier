package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.manage.dao.ConstructionUnitMapper;
import cn.edu.upc.manage.dao.PostMapper;
import cn.edu.upc.manage.dao.UserMapper;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.model.UserWithUnitName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConstructionUnitMapper constructionUnitMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    public User selectByPrimaryKey(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserPassword(User recordPassword) {
//        if(recordPassword.getOperator()=="admin"){
            userMapper.updateByPrimaryKeySelective(recordPassword);
//        }

    }

    @Transactional
    @Override
    public void deleteUser(int userId){
        userMapper.deleteUser(userId);
    }

    @Override
    public List<UserWithUnitName> getAllUser(){
        List<UserWithUnitName> userList=userMapper.getAllUser();
        for (UserWithUnitName p:userList
        ) {
            if(p.getUserType()==2){
                p.setUnitName(constructionUnitMapper.getUnitNameById(p.getDepartmentUnitId()));
//                p.setUnitName(constructionUnitMapper.selectByPrimaryKey(p.getDepartmentUnitId()).getName());
            }else {
                String unitName=postMapper.selectByPrimaryKey(p.getDepartmentUnitId()).getPostName();
                p.setUnitName(unitName);

            }
        }
        return userList;
    }

    @Override
    public List<UserWithUnitName> selectByUsername(String username){
        List<UserWithUnitName> userList=userMapper.getByUsername(username);
        for (UserWithUnitName p:userList
        ) {
            if(p.getUserType()==2){
                p.setUnitName(constructionUnitMapper.selectByPrimaryKey(p.getDepartmentUnitId()).getName());
            }else {
                String unitName=postMapper.selectByPrimaryKey(p.getDepartmentUnitId()).getPostName();
                p.setUnitName(unitName);

            }
        }
        return userList;
    }
}
