package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.RightRole;
import cn.edu.upc.manage.model.Role;
import cn.edu.upc.manage.model.ViewRightsRole;

import java.util.List;

public interface RoleService {
    public List<ViewRightsRole> getAllRole();
    public void deleteRole(int roleId);
    public int insertNewRole(Role role);
    public void insertNewRightRole(RightRole rightRole);
    public void deleteRightRole(int roleId);
    public void updatetRole(Role role);
    public void updatetRoleName(int id,String roleName);

}
