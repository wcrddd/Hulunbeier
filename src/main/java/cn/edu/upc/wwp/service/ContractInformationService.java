package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.model.ContractInformation;
import cn.edu.upc.manage.model.ContractInformationWithTenderId;
import cn.edu.upc.manage.model.ContractStatistics;
import cn.edu.upc.manage.model.ContractWithProjectName;

import javax.annotation.Resource;
import java.util.List;

public interface ContractInformationService {


    public void updateContractInformation(ContractInformation recordUp);
    public int insertContractInformation(ContractInformationWithTenderId recordIn);
    public void deleteFlag(ContractInformation recordDel);
    public List<ContractInformation> getAllContractInformation();
    public List<ContractInformation> getContractByProjectId(int projectId);
    public ContractInformation getContractBytId(int id);
    public ContractStatistics getContractStatistics(int projectId);
    public List<ContractInformation> getContractByTenderId(int tenderId);
    public List<ContractWithProjectName> getAllContractWithProjectName();
    public List<ContractWithProjectName> getCompletedByUnitId(int unitId);
}
