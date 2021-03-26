package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.dao.ContractInformationMapper;
import cn.edu.upc.manage.model.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

public interface ContractInformationService {


    /**
     * 修改合同
     * @param projectSectionList
     */
    public void updateContractInformation(List<ProjectSection> projectSectionList);

    /**
     * 上传合同
     * @param projectSectionList
     * @return
     */
    public void insertContractInformation(List<ProjectSection> projectSectionList);
    public void deleteFlag(ContractInformation recordDel);
    public List<ContractInformation> getAllContractInformation();

    /**
     * 根据项目id获取
     * @param projectId
     * @return
     */
    public List<ProjectSection> getContractByProjectId(int projectId);
    public ContractInformation getContractBytId(int id);
    public ContractStatistics getContractStatistics(int projectId);
    public ContractInformation getContractByTenderId(int tenderId);
    public List<ContractWithProjectName> getAllContractWithProjectName();
    public List<ContractWithProjectName> getCompletedByUnitId(int unitId);
    public void updateApprove(ProjectSection projectSection);
    public List<ContractWithProjectName> getCanProgress(int unitId);
    public List<ContractWithProjectName> selectCanProgress(int unitId,String projectName);

    /**
     * 追加合同
     * @param projectSection
     */
    void continueInsertContractInformation(ProjectSection projectSection);
}
