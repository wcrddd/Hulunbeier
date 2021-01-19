package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.dzh.until.Message;
import cn.edu.upc.manage.dao.PredesignReportAppendixMapper;
import cn.edu.upc.manage.dao.PredesignReportMapper;
import cn.edu.upc.manage.dao.ProjectSectionMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.mo.PredesignReportMo;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.model.ProjectSection;
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

    @Transactional
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
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),5);
        }else {
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),6);
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
        projectStoreMapper.updatePlanedFlag(predesignReportVo.getProjectId(),4);
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
        projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),7);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateKeepApprove(PredesignReport predesignReport){
        predesignReportMapper.updateByPrimaryKeySelective(predesignReport);
        int approve = predesignReport.getKeepApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),8);
        }else {
            projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),9);
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
        projectStoreMapper.updatePlanedFlag(projectSection.getProjectId(),4);
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
        projectStoreMapper.updatePlanedFlag(predesignReport.getProjectId(),4);
    }

}
