package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RightService;
import cn.edu.upc.manage.dao.RightMapper;
import cn.edu.upc.manage.model.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RightServiceImpl implements RightService {
    @Autowired
    RightMapper rightMapper;

    @Transactional
    @Override
    public void insertRight(Right right){
        rightMapper.insertSelective(right);
    }

    @Override
    public List<Right> getAllRight(){
        return rightMapper.getAllRight();
    }

    @Transactional
    @Override
    public void updateRight(Right right){
        rightMapper.updateByPrimaryKeySelective(right);
    }

    @Transactional
    @Override
    public void deleteRight(int rightId){
        rightMapper.deleteRight(rightId);
    }
}
