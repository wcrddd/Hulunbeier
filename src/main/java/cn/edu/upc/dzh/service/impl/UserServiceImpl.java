package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.UserService;
import cn.edu.upc.manage.dao.UserMapper;
import cn.edu.upc.manage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserPassword(User recordPassword) {
        if(recordPassword.getOperator()=="admin"){
            userMapper.updateByPrimaryKeySelective(recordPassword);
        }

    }
}
