package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RoleService;
import cn.edu.upc.manage.dao.RightRoleMapper;
import cn.edu.upc.manage.dao.RoleMapper;
import cn.edu.upc.manage.dao.ViewRightsRoleMapper;
import cn.edu.upc.manage.model.RightRole;
import cn.edu.upc.manage.model.Role;
import cn.edu.upc.manage.model.ViewRightsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RightRoleMapper rightRoleMapper;
    @Autowired
    private ViewRightsRoleMapper viewRightsRoleMapper;

    @Override
    public List<ViewRightsRole> getAllRole(){
        return viewRightsRoleMapper.getAllRoleRights();
    }

    @Transactional
    @Override
    public void deleteRole(int roleId){
        roleMapper.deleteRole(roleId);
    }

    @Transactional
    @Override
    public int insertNewRole(Role role){
        roleMapper.insertSelective(role);
        return roleMapper.selectLastInsert();
    }

    @Transactional
    @Override
    public void insertNewRightRole(RightRole rightRole){
        rightRoleMapper.insertSelective(rightRole);
    }

    @Transactional
    @Override
    public void deleteRightRole(int roleId){
        rightRoleMapper.deleteByRoleId(roleId);
    }

    @Transactional
    @Override
    public void updatetRole(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    @Transactional
    @Override
    public void updatetRoleName(int id,String roleName){
        roleMapper.updatetRoleName(id,roleName);
    }
}
