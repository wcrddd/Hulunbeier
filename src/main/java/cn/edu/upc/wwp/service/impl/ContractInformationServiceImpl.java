package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.model.ContractInformation;
import cn.edu.upc.wwp.service.ContractInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("contractInformationService")
public class ContractInformationServiceImpl  implements ContractInformationService {

    @Resource
    ContractInformationMapper contractInformationMapper;

    @Override
    public void updateContractInformation(ContractInformation recordUp) {
        recordUp.setOperator("test");
        contractInformationMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public int insertContractInformation(ContractInformation recordIn) {
        recordIn.setOperator("test");
        contractInformationMapper.insertSelective(recordIn);
        return contractInformationMapper.selectLastInsert();
    }

    @Override
    public void deleteFlag(ContractInformation recordDel) {
        ContractInformation result=contractInformationMapper.selectByPrimaryKey(recordDel.getId());
        if(result!=null){
            recordDel.setDelFlag(1);
            contractInformationMapper.updateByPrimaryKeySelective(recordDel);
        }
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
}
