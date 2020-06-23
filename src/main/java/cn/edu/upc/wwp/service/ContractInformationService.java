package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.model.ContractInformation;

import javax.annotation.Resource;
import java.util.List;

public interface ContractInformationService {


    public void updateContractInformation(ContractInformation recordUp);
    public int insertContractInformation(ContractInformation recordIn);
    public void deleteFlag(ContractInformation recordDel);
    public List<ContractInformation> getAllContractInformation();
    public List<ContractInformation> getContractByProjectId(int projectId);
    public ContractInformation getContractBytId(int id);
}
