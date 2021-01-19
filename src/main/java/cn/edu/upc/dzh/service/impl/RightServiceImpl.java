package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.RightService;
import cn.edu.upc.manage.dao.RightMapper;
import cn.edu.upc.manage.dao.RightsMapper;
import cn.edu.upc.manage.dao.ViewRightsMapper;
import cn.edu.upc.manage.model.Right;
import cn.edu.upc.manage.model.Rights;
import cn.edu.upc.manage.model.ViewRights;
import cn.edu.upc.manage.vo.RightA;
import cn.edu.upc.manage.vo.RightB;
import cn.edu.upc.manage.vo.RightC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public List<RightA> getAllRightTree(){
        List<RightA> rightAList = new ArrayList<>();
        List<Rights> rightsListA = rightsMapper.getRightA();
        for (Rights item:rightsListA
             ) {
            RightA rightA = new RightA();
            rightA.setValue(item.getId());
            rightA.setKey(item.getId());
            rightA.setLabel(item.getRightName());
            List<Rights> rightsListB = rightsMapper.getRightB(item.getId());
            List<RightB> rightBList = new ArrayList<>();
            for (Rights item2:rightsListB
                 ) {
                RightB rightB = new RightB();
                rightB.setValue(item2.getId());
                rightB.setKey(item2.getId());
                rightB.setLabel(item2.getRightName());
                List<Rights> rightsListC = rightsMapper.getRightB(item2.getId());
                List<RightC> rightCList = new ArrayList<>();
                if (rightsListC.size() != 0){
                    for (Rights item3:rightsListC
                         ) {
                        RightC rightC = new RightC();
                        rightC.setValue(item3.getId());
                        rightC.setKey(item3.getId());
                        rightC.setLabel(item3.getRightName());
                        rightCList.add(rightC);
                    }
                }
                rightB.setChildren(rightCList);
                rightBList.add(rightB);
            }
            rightA.setChildren(rightBList);
            rightAList.add(rightA);
        }
        return rightAList;
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

    @Transactional
    @Override
    public void insertRight2(Rights right){
        rightsMapper.insertSelective(right);
    }

    @Override
    public List<ViewRights> selectRightByRole(int roleId){
        return viewRightsMapper.selectRightByRole(roleId);
    }
}
