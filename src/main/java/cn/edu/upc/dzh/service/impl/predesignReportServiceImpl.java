package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.dzh.until.Message;
import cn.edu.upc.manage.dao.*;
import cn.edu.upc.manage.mo.PredesignReportMo;
import cn.edu.upc.manage.model.*;
import cn.edu.upc.manage.vo.PredesignReportVo;
import cn.edu.upc.manage.vo.PredesignReportWithProject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class predesignReportServiceImpl implements PredesignReportService {
    @Autowired
    private PredesignReportAppendixMapper predesignReportAppendixMapper;
    @Autowired
    private PredesignReportMapper predesignReportMapper;
    @Autowired
    private ProjectSectionMapper projectSectionMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;
    @Autowired
    private ProjectVerificationMapper projectVerificationMapper;

    @Transactional
    @Override
    public void insertAppendix(PredesignReportAppendix predesignReportAppendix){
        int projectId=predesignReportAppendix.getProjectId();
        PredesignReport predesignReport=new PredesignReport();
        predesignReport.setProjectId(projectId);
        if(predesignReportMapper.selectByProjectId(projectId)==null)
        {predesignReportMapper.insertSelective(predesignReport);}
        predesignReportMapper.resetApprove(projectId);
        predesignReportAppendixMapper.insertSelective(predesignReportAppendix);
    }

    @Override
    public List<PredesignReportAppendix> getAppendixByProjectId(int projectId){
        return predesignReportAppendixMapper.getAppendixByProjectId(projectId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAppendix(PredesignReportAppendix predesignReportAppendix){
         predesignReportAppendixMapper.updateByPrimaryKeySelective(predesignReportAppendix);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateApproveExamine(PredesignReport predesignReport){
        predesignReport.setApproveTime(new Date());
        predesignReportMapper.updateApproveExamine2(predesignReport);
        int approve = predesignReport.getApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),5,6);
//            List<ProjectVerification> projectVerificationList = new ArrayList<>();
//            ProjectVerification projectVerification = new ProjectVerification();
//            projectVerification.setProjectId(predesignReport.getProjectId());
//            projectVerification.setVerificationFlag(0);
//            projectVerification.setVerificationName("审价1");
//            projectVerificationList.add(projectVerification);
//            ProjectVerification projectVerification2 = new ProjectVerification();
//            projectVerification2.setProjectId(predesignReport.getProjectId());
//            projectVerification2.setVerificationFlag(0);
//            projectVerification2.setVerificationName("审价2");
//            projectVerificationList.add(projectVerification2);
//            ProjectVerification projectVerification3 = new ProjectVerification();
//            projectVerification3.setProjectId(predesignReport.getProjectId());
//            projectVerification3.setVerificationFlag(0);
//            projectVerification3.setVerificationName("审价3");
//            projectVerificationList.add(projectVerification3);
//            ProjectVerification projectVerification4 = new ProjectVerification();
//            projectVerification4.setProjectId(predesignReport.getProjectId());
//            projectVerification4.setVerificationFlag(0);
//            projectVerification4.setVerificationName("审价4");
//            projectVerificationList.add(projectVerification4);
//            ProjectVerification projectVerification5 = new ProjectVerification();
//            projectVerification5.setProjectId(predesignReport.getProjectId());
//            projectVerification5.setVerificationFlag(0);
//            projectVerification5.setVerificationName("审价5");
//            projectVerificationList.add(projectVerification5);
//            ProjectVerification projectVerification6 = new ProjectVerification();
//            projectVerification6.setProjectId(predesignReport.getProjectId());
//            projectVerification6.setVerificationFlag(0);
//            projectVerification6.setVerificationName("审价6");
//            projectVerificationList.add(projectVerification6);
//            ProjectVerification projectVerification7 = new ProjectVerification();
//            projectVerification7.setProjectId(predesignReport.getProjectId());
//            projectVerification7.setVerificationFlag(0);
//            projectVerification7.setVerificationName("审价7");
//            projectVerificationList.add(projectVerification7);
//            ProjectVerification projectVerification8 = new ProjectVerification();
//            projectVerification8.setProjectId(predesignReport.getProjectId());
//            projectVerification8.setVerificationFlag(0);
//            projectVerification8.setVerificationName("审价8");
//            projectVerificationList.add(projectVerification8);
//            ProjectVerification projectVerification9 = new ProjectVerification();
//            projectVerification9.setProjectId(predesignReport.getProjectId());
//            projectVerification9.setVerificationFlag(0);
//            projectVerification9.setVerificationName("审价9");
//            projectVerificationList.add(projectVerification9);
//            ProjectVerification projectVerification10 = new ProjectVerification();
//            projectVerification10.setProjectId(predesignReport.getProjectId());
//            projectVerification10.setVerificationFlag(0);
//            projectVerification10.setVerificationName("审价10");
//            projectVerificationList.add(projectVerification10);
//            ProjectVerification projectVerification11 = new ProjectVerification();
//            projectVerification11.setProjectId(predesignReport.getProjectId());
//            projectVerification11.setVerificationFlag(0);
//            projectVerification11.setVerificationName("审价11");
//            projectVerificationList.add(projectVerification11);
//            ProjectVerification projectVerification12 = new ProjectVerification();
//            projectVerification12.setProjectId(predesignReport.getProjectId());
//            projectVerification12.setVerificationFlag(0);
//            projectVerification12.setVerificationName("审价12");
//            projectVerificationList.add(projectVerification12);
//            projectVerificationMapper.insertVerificationList(projectVerificationList);
        }else {
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),6,5);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateApproveExamine2(){
        List<ProjectStore> projectStores = new ArrayList<>();
        projectStores = projectStoreMapper.getTest();
        for (ProjectStore item:projectStores
             ) {
            if(item.getId()!=170 && item.getId() != 174){
                List<ProjectVerification> projectVerificationList = new ArrayList<>();
                ProjectVerification projectVerification = new ProjectVerification();
                projectVerification.setProjectId(item.getId());
                projectVerification.setVerificationFlag(0);
                projectVerification.setVerificationName("审价1");
                projectVerificationList.add(projectVerification);
                ProjectVerification projectVerification2 = new ProjectVerification();
                projectVerification2.setProjectId(item.getId());
                projectVerification2.setVerificationFlag(0);
                projectVerification2.setVerificationName("审价2");
                projectVerificationList.add(projectVerification2);
                ProjectVerification projectVerification3 = new ProjectVerification();
                projectVerification3.setProjectId(item.getId());
                projectVerification3.setVerificationFlag(0);
                projectVerification3.setVerificationName("审价3");
                projectVerificationList.add(projectVerification3);
                ProjectVerification projectVerification4 = new ProjectVerification();
                projectVerification4.setProjectId(item.getId());
                projectVerification4.setVerificationFlag(0);
                projectVerification4.setVerificationName("审价4");
                projectVerificationList.add(projectVerification4);
                ProjectVerification projectVerification5 = new ProjectVerification();
                projectVerification5.setProjectId(item.getId());
                projectVerification5.setVerificationFlag(0);
                projectVerification5.setVerificationName("审价5");
                projectVerificationList.add(projectVerification5);
                ProjectVerification projectVerification6 = new ProjectVerification();
                projectVerification6.setProjectId(item.getId());
                projectVerification6.setVerificationFlag(0);
                projectVerification6.setVerificationName("审价6");
                projectVerificationList.add(projectVerification6);
                ProjectVerification projectVerification7 = new ProjectVerification();
                projectVerification7.setProjectId(item.getId());
                projectVerification7.setVerificationFlag(0);
                projectVerification7.setVerificationName("审价7");
                projectVerificationList.add(projectVerification7);
                ProjectVerification projectVerification8 = new ProjectVerification();
                projectVerification8.setProjectId(item.getId());
                projectVerification8.setVerificationFlag(0);
                projectVerification8.setVerificationName("审价8");
                projectVerificationList.add(projectVerification8);
                ProjectVerification projectVerification9 = new ProjectVerification();
                projectVerification9.setProjectId(item.getId());
                projectVerification9.setVerificationFlag(0);
                projectVerification9.setVerificationName("审价9");
                projectVerificationList.add(projectVerification9);
                ProjectVerification projectVerification10 = new ProjectVerification();
                projectVerification10.setProjectId(item.getId());
                projectVerification10.setVerificationFlag(0);
                projectVerification10.setVerificationName("审价10");
                projectVerificationList.add(projectVerification10);
                ProjectVerification projectVerification11 = new ProjectVerification();
                projectVerification11.setProjectId(item.getId());
                projectVerification11.setVerificationFlag(0);
                projectVerification11.setVerificationName("审价11");
                projectVerificationList.add(projectVerification11);
                ProjectVerification projectVerification12 = new ProjectVerification();
                projectVerification12.setProjectId(item.getId());
                projectVerification12.setVerificationFlag(0);
                projectVerification12.setVerificationName("审价12");
                projectVerificationList.add(projectVerification12);
                projectVerificationMapper.insertVerificationList(projectVerificationList);

            }
        }

    }

    @Override
    public List<PredesignReportWithProject> getAllpredesignReport(){
        return predesignReportMapper.getAllpredesignReport();
    }

    @Override
    public List<PredesignReportWithProject> getCanApproveByUnitId(int unitId){
        return predesignReportMapper.getCanApproveByUnitId(unitId);
    }

    @Override
    public List<PredesignReportWithProject> selectCanApprove(int unitId,String projectName){
        return predesignReportMapper.selectCanApprove(unitId,projectName);
    }

    @Override
    public List<PredesignReportWithProject> selectAllpredesignReport(String projectName){
        return predesignReportMapper.selectAllpredesignReport(projectName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertReport(PredesignReportVo predesignReportVo){
        PredesignReport predesignReport = new PredesignReport();
        BeanUtils.copyProperties(predesignReportVo,predesignReport);

        List<ProjectSection> projectSectionList = predesignReportVo.getProjectSectionList();
        for (ProjectSection item:projectSectionList
             ) {
            if (item.getTenderFlag() == 0){
                predesignReport.setTenderFlag(0);
                projectStoreMapper.updateTenderFlag(predesignReportVo.getProjectId());
                break;
            }
        }
        predesignReportMapper.insertSelective(predesignReport);
        projectSectionMapper.insertList(projectSectionList);
        projectStoreMapper.updatePlanedFlag(predesignReportVo.getProjectId(),4,4);
        projectStoreMapper.updateSectionNum(predesignReportVo.getProjectId(), projectSectionMapper.count(predesignReportVo.getProjectId(),1));
        Message.send("13061491890",predesignReport.getProjectName());
    }

    @Override
    public PredesignReportMo getByProjectId(int projectId){
        PredesignReportMo predesignReportMo = new PredesignReportMo();
        PredesignReport predesignReport = predesignReportMapper.getByProjectId(projectId);
        if (predesignReport != null) {
            BeanUtils.copyProperties(predesignReport, predesignReportMo);
            List<ProjectSection> projectSectionList = projectSectionMapper.getSectionByProjectId(projectId);
            predesignReportMo.setProjectSectionList(projectSectionList);
            return predesignReportMo;
        }else {
            return null;
        }



    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertKeepRecord(PredesignReport predesignReport){
        predesignReport.setKeepApprove(0);
        predesignReportMapper.updateByPrimaryKeySelective(predesignReport);
        projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),7,7);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateKeepApprove(PredesignReport predesignReport){
        predesignReportMapper.updateByPrimaryKeySelective(predesignReport);
        int approve = predesignReport.getKeepApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),8,9);
        }else {
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),9,8);
        }
    }

    @Override
    public PredesignReport getKeepByProjectId(int projectId){
        return predesignReportMapper.getKeepByProjectId(projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSection(int sectionId){
        projectSectionMapper.deleteSection(sectionId);
        ProjectSection projectSection = projectSectionMapper.selectByPrimaryKey(sectionId);
        predesignReportMapper.updateApproveFlag(projectSection.getProjectId());
        projectStoreMapper.updatePlanedFlag(projectSection.getProjectId(),4,4);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReport( PredesignReportVo predesignReportVo){
        PredesignReport predesignReport = new PredesignReport();
        BeanUtils.copyProperties(predesignReportVo,predesignReport);

        List<ProjectSection> projectSectionList = predesignReportVo.getProjectSectionList();
        for (ProjectSection item:projectSectionList
        ) {
            if (item.getTenderFlag() == 0){
                predesignReport.setTenderFlag(0);
                projectStoreMapper.updateTenderFlag(predesignReportVo.getProjectId());
                break;
            }
        }
        predesignReport.setApprove(0);
        predesignReportMapper.updateByPrimaryKeySelective(predesignReport);
        projectSectionMapper.deleteByprojectId(predesignReport.getProjectId());
        projectSectionMapper.insertList(projectSectionList);
        projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),4,4);
    }

}
