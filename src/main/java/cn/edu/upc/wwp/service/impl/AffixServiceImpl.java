package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.AffixMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.model.Affix;
import cn.edu.upc.wwp.service.AffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service("affixService")
public class AffixServiceImpl implements AffixService {

    @Resource
    AffixMapper affixMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;

    @Override
    public List<Affix> selectAffix() {

        return affixMapper.selectAffix();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAffix(Affix recordUp) {
        recordUp.setApprove(0);
        affixMapper.updateByPrimaryKeySelective(recordUp);
        projectStoreMapper.updatePlanedFlag(recordUp.getProjectId(),20,20);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAffix(Affix recordIn) {
        affixMapper.insertSelective(recordIn);
        projectStoreMapper.updatePlanedFlag(recordIn.getProjectId(),20,20);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApprove(Affix affix){
        affix.setApproveTime(new Date());
        affixMapper.updateApprove(affix);
        int approve = affix.getApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(affix.getProjectId(),21,22);
        }else {
            projectStoreMapper.updatePlanedFlag(affix.getProjectId(),22,21);
        }
    }

    @Override
    public List<Affix> getAllReport(){
        return affixMapper.getAllReport();
    }

    @Override
    public void deleteFlag(Affix recordDel) {

            recordDel.setDelFlag(recordDel.getId());
            affixMapper.updateByPrimaryKeySelective(recordDel);

    }

    @Override
    public List<Affix> getAffixByContractId(int contractId){
        return affixMapper.getAffixByContractId(contractId);
    }

    @Override
    public List<Affix> getByProjectId(int projectId){
        return affixMapper.getByProjectId(projectId);
    }
}

