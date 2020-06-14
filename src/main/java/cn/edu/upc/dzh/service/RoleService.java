package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRole();
    public void deleteRole(int roleId);

}
