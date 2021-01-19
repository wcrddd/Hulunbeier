package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.*;
import cn.edu.upc.manage.model.*;
import cn.edu.upc.wwp.service.ContractInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("contractInformationService")
public class ContractInformationServiceImpl  implements ContractInformationService {

    @Autowired
    private ContractInformationMapper contractInformationMapper;
    @Autowired
    private ContractTenderRelationMapper contractTenderRelationMapper;
    @Autowired
    private ContractStatisticsMapper contractStatisticsMapper;
    @Autowired
    private ProjectSectionMapper projectSectionMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateContractInformation(List<ProjectSection> projectSectionList) {
        for (ProjectSection item:projectSectionList
             ) {
            item.setContractApprove(0);
        }
//        projectSection.setContractApprove(0);
        projectSectionMapper.updateContractList(projectSectionList);
        projectStoreMapper.updatePlanedFlag(projectSectionList.get(0).getProjectId(),13);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApprove(ProjectSection projectSection) {
        projectSection.setContractApproveTime(new Date());
        projectSectionMapper.updateByPrimaryKeySelective(projectSection);
        int projectId = projectSection.getProjectId();
        int approve = projectSection.getContractApprove();
        int sectionNum = projectSectionMapper.count(projectId,1);
        int sectionContractApproveNum = projectSectionMapper.count(projectId,4);
        if (sectionNum == sectionContractApproveNum){

//            if (approve == 1){
                projectStoreMapper.updatePlanedFlag(projectId,14);
//            }else {
//                projectStoreMapper.updatePlanedFlag(projectId,15);
//            }
        }else if(approve == 2){
            projectStoreMapper.updatePlanedFlag(projectId,15);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertContractInformation(List<ProjectSection> projectSectionList) {

//        contractInformationMapper.insertSelective(recordIn);
//        int contractInformationId=contractInformationMapper.selectLastInsert();
//        ContractTenderRelation contractTenderRelation=new ContractTenderRelation();
//        contractTenderRelation.setTenderId(recordIn.getTenderId());
//        contractTenderRelation.setContractId(contractInformationId);
//        contractTenderRelationMapper.insertSelective(contractTenderRelation);
//        return contractInformationId;
        for (ProjectSection item:projectSectionList
             ) {
            item.setContractTime(new Date());
        }
        projectSectionMapper.updateTenderList(projectSectionList);
//        projectSectionMapper.insertContract(projectSection);
        int projectId = projectSectionList.get(0).getProjectId();
//        projectSectionMapper.updateByPrimaryKeySelective(projectSection);
        projectStoreMapper.updatePlanedFlag(projectId,13);

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

    /**
     * 根据项目id获取合同信息
     * @param projectId
     * @return
     */
    @Override
    public List<ProjectSection> getContractByProjectId(int projectId){
        return projectSectionMapper.getContractByProjectId(projectId);
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
    public ContractInformation getContractByTenderId(int tenderId){
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

    @Override
    public List<ContractWithProjectName> getCanProgress(int unitId){
        return contractInformationMapper.getCanProgress(unitId);
    }

    @Override
    public List<ContractWithProjectName> selectCanProgress(int unitId,String projectName){
        return contractInformationMapper.selectCanProgress(unitId,projectName);
    }
}
