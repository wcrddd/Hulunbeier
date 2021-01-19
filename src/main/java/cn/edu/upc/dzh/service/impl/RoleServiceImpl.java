package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RoleService;
import cn.edu.upc.manage.dao.*;
import cn.edu.upc.manage.model.*;
import cn.edu.upc.manage.vo.ViewRightsIdRole;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RightRoleMapper rightRoleMapper;
    @Autowired
    private ViewRightsRoleMapper viewRightsRoleMapper;
    @Autowired
    private ViewRightsIdRoleIdMapper viewRightsIdRoleIdMapper;
    @Autowired
    private RightsMapper rightsMapper;

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

    @Override
    public List<ViewRightsRole> selectByName(String name){
        return viewRightsRoleMapper.selectByName(name);
    }

    @Override
    public List<ViewRightsIdRoleIdWithBLOBs> getAllRoleRightsId(){
        return viewRightsIdRoleIdMapper.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertNewRole2(String roleName, JSONArray rightsList){
        Role role =new Role();
        role.setRoleName(roleName);
        roleMapper.insertSelective(role);
        int roleId = roleMapper.selectLastInsert();
        List<Integer> lastIdList = new ArrayList<>();
        for(int i=0;i<rightsList.size();i++){
            int rightId=rightsList.getInteger(i);
            Rights right = rightsMapper.selectByPrimaryKey(rightId);
            if (right.getLastId() != rightId) {
                RightRole rightRole =new RightRole();
                rightRole.setRightId(rightId);
                rightRole.setRoleId(roleId);
                rightRoleMapper.insertSelective(rightRole);
                if (!lastIdList.contains(right.getLastId())){
                    rightRole.setRightId(right.getLastId());
                    rightRoleMapper.insertSelective(rightRole);
                    lastIdList.add(right.getLastId());
                }

            }else {
                List<Rights> rightList = rightsMapper.getByLastId(rightId);
                for (int j = 0; j<rightList.size(); j++){
                    RightRole rightRole =new RightRole();
                    rightRole.setRightId(rightList.get(j).getId());
                    rightRole.setRoleId(roleId);
                    rightRoleMapper.insertSelective(rightRole);
                }
            }

        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole2(int roleId, String roleName, JSONArray rightsList){
        rightRoleMapper.deleteByRoleId(roleId);
        roleMapper.updatetRoleName(roleId,roleName);
//        Role role =new Role();
//        role.setRoleName(roleName);
//        roleMapper.insertSelective(role);
//        int roleId = roleMapper.selectLastInsert();
        List<Integer> lastIdList = new ArrayList<>();
        for(int i=0;i<rightsList.size();i++){
            int rightId=rightsList.getInteger(i);
            Rights right = rightsMapper.selectByPrimaryKey(rightId);
            System.out.println("权限名称"+right.getRightName());
            System.out.println();
            if (right.getLastId() != rightId) {
                RightRole rightRole =new RightRole();
                rightRole.setRightId(rightId);
                rightRole.setRoleId(roleId);
                rightRoleMapper.insertSelective(rightRole);
                if (!lastIdList.contains(right.getLastId())){
                    rightRole.setRightId(right.getLastId());
                    rightRoleMapper.insertSelective(rightRole);
                    lastIdList.add(right.getLastId());
                }

            }else {
                List<Rights> rightList = rightsMapper.getByLastId(rightId);
                for (int j = 0; j<rightList.size(); j++){
                    RightRole rightRole =new RightRole();
                    rightRole.setRightId(rightList.get(j).getId());
                    rightRole.setRoleId(roleId);
                    rightRoleMapper.insertSelective(rightRole);
                }
            }

        }
    }
}
