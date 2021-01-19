package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.UserUnitName;

public interface RegisterLoginService {
    public void insertUser(User user);
    public User selectByUsername(String username);
    public void changePasswordByEmail(String newPassword,String email);
    public User selectByEmail(String email);
    public UserUnitName getUserWithUnitName(User user);

    User selectByTel(String tel);
}
