package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.RightRole;
import cn.edu.upc.manage.model.Role;
import cn.edu.upc.manage.model.ViewRightsIdRoleIdWithBLOBs;
import cn.edu.upc.manage.model.ViewRightsRole;
import cn.edu.upc.manage.vo.ViewRightsIdRole;

import java.util.List;

public interface RoleService {
    public List<ViewRightsRole> getAllRole();
    public void deleteRole(int roleId);
    public int insertNewRole(Role role);
    public void insertNewRightRole(RightRole rightRole);
    public void deleteRightRole(int roleId);
    public void updatetRole(Role role);
    public void updatetRoleName(int id,String roleName);
    public List<ViewRightsRole> selectByName(String name);
    public List<ViewRightsIdRoleIdWithBLOBs> getAllRoleRightsId();

}
