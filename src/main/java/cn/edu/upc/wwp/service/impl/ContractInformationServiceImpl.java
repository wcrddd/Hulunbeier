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
    public void insertContractInformation(ContractInformation recordIn) {
        recordIn.setOperator("test");
        contractInformationMapper.insertSelective(recordIn);
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
}
