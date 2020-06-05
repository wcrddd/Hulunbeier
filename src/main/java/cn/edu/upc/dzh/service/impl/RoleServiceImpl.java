package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RoleService;
import cn.edu.upc.manage.dao.RoleMapper;
import cn.edu.upc.manage.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRole(){
        return roleMapper.getAllRole();
    }

    @Transactional
    @Override
    public void deleteRole(int roleId){
        roleMapper.deleteRole(roleId);
    }
}
