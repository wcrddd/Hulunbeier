package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.dao.ContractStatisticsMapper;
import cn.edu.upc.manage.dao.ContractTenderRelationMapper;
import cn.edu.upc.manage.model.*;
import cn.edu.upc.wwp.service.ContractInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("contractInformationService")
public class ContractInformationServiceImpl  implements ContractInformationService {

    @Resource
    ContractInformationMapper contractInformationMapper;
    @Autowired
    ContractTenderRelationMapper contractTenderRelationMapper;
    @Autowired
    ContractStatisticsMapper contractStatisticsMapper;

    @Override
    public void updateContractInformation(ContractInformation recordUp) {
        recordUp.setApprove(0);
        recordUp.setOperator("test");
        contractInformationMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public int insertContractInformation(ContractInformationWithTenderId recordIn) {
        recordIn.setOperator("test");
        contractInformationMapper.insertSelective(recordIn);
        int contractInformationId=contractInformationMapper.selectLastInsert();
        ContractTenderRelation contractTenderRelation=new ContractTenderRelation();
        contractTenderRelation.setTenderId(recordIn.getTenderId());
        contractTenderRelation.setContractId(contractInformationId);
        contractTenderRelationMapper.insertSelective(contractTenderRelation);
        return contractInformationId;
    }

    @Override
    public void deleteFlag(ContractInformation recordDel) {
//        ContractInformation result=contractInformationMapper.selectByPrimaryKey(recordDel.getId());
//        if(result!=null){
            recordDel.setDelFlag(recordDel.getId());
            contractInformationMapper.updateByPrimaryKeySelective(recordDel);
//        }
    }

    @Override
    public List<ContractInformation> getAllContractInformation(){
        return contractInformationMapper.getAllContractInformation();
    }

    @Override
    public List<ContractInformation> getContractByProjectId(int projectId){
        return contractInformationMapper.getContractByProjectId(projectId);
    }

    @Override
    public ContractInformation getContractBytId(int id){
        return contractInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public ContractStatistics getContractStatistics(int projectId){
        return contractStatisticsMapper.getContractStatistics(projectId);
    }

    @Override
    public List<ContractInformation> getContractByTenderId(int tenderId){
        return contractInformationMapper.getContractByTenderId(tenderId);
    }

    @Override
    public List<ContractWithProjectName> getAllContractWithProjectName(){
        return contractInformationMapper.getAllContractWithProjectName();
    }

    @Override
    public List<ContractWithProjectName> getCompletedByUnitId(int unitId){
        return contractInformationMapper.getCompletedByUnitId(unitId);
    }
}
