package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RightService;
import cn.edu.upc.manage.dao.RightMapper;
import cn.edu.upc.manage.dao.RightsMapper;
import cn.edu.upc.manage.dao.ViewRightsMapper;
import cn.edu.upc.manage.model.Rights;
import cn.edu.upc.manage.model.ViewRights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RightServiceImpl implements RightService {
    @Autowired
    RightsMapper rightsMapper;
    @Autowired
    ViewRightsMapper viewRightsMapper;

    @Transactional
    @Override
    public void insertRight(Rights right){
        rightsMapper.insert(right);
    }

    @Override
    public List<ViewRights> getAllRight(){
        return viewRightsMapper.getAllRight();
    }

    @Transactional
    @Override
    public void updateRight(Rights right){
        rightsMapper.updateByPrimaryKeySelective(right);
    }

    @Transactional
    @Override
    public void deleteRight(int rightId){
        rightsMapper.deleteRight(rightId);
    }

    @Override
    public List<ViewRights> selectByName(String name){
        return viewRightsMapper.getRightByname(name);
    }
}
