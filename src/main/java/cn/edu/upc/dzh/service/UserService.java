package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.User;

public interface UserService {
    public User selectByPrimaryKey(Integer id);
    public void updateUserPassword(User recordPassword);
    public void deleteUser(int userId);
}
