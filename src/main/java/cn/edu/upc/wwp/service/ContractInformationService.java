package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.model.ContractInformation;

import javax.annotation.Resource;

public interface ContractInformationService {


    public void updateContractInformation(ContractInformation recordUp);
    public void insertContractInformation(ContractInformation recordIn);
    public void deleteFlag(ContractInformation recordDel);
}
