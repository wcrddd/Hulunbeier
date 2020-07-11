package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.model.UserWithUnitName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public User selectByPrimaryKey(Integer id);
    public void updateUserPassword(User recordPassword);
    public void deleteUser(int userId);
    public List<UserWithUnitName> getAllUser();
    public List<UserWithUnitName> selectByUsername(String username);
    public User getByUsername(String username);
    public void changePasswordByUsername(String newPassword, String username);

}
