package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.dao.FeasibilityResearchReportMapper;
import cn.edu.upc.manage.dao.StudyReportMapper;
import cn.edu.upc.manage.dao.ViewStudyReportMapper;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import cn.edu.upc.manage.model.ViewStudyReport;
import cn.edu.upc.manage.vo.FeasibilityProjectName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyReportServiceImpl implements StudyReportService {
    @Autowired
    private StudyReportMapper studyReportMapper;
    @Autowired
    private FeasibilityResearchReportMapper feasibilityResearchReportMapper;
    @Autowired
    private ViewStudyReportMapper viewStudyReportMapper;


    @Transactional
    @Override
    public void insertAppendix(StudyReport studyReport){
        studyReportMapper.insertSelective(studyReport);
    }

    @Transactional
    @Override
    public void insertReport(FeasibilityResearchReport feasibilityResearchReport){
        int projectId=feasibilityResearchReport.getProjectId();
        if(feasibilityResearchReportMapper.getByProjectId(projectId)==null)
        {feasibilityResearchReportMapper.insertSelective(feasibilityResearchReport);}
    }

    @Override
    public List<ViewStudyReport> getReportByProjectId(int projectId){
        return viewStudyReportMapper.getReportByProjectId(projectId);
    }

    @Transactional
    @Override
    public void deleteAppendix(StudyReport studyReport){
        studyReport.setDelFlag(studyReport.getId());
        studyReportMapper.updateByPrimaryKeySelective(studyReport);
    }

    @Transactional
    @Override
    public void updateReport(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReportMapper.updateReport(feasibilityResearchReport);
    }

    @Override
    public void updateApproveExamine(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReportMapper.updateByPrimaryKeySelective(feasibilityResearchReport);
    }

    @Override
    public List<FeasibilityProjectName> getFeasibilityByUnitId(int unitId){
        return feasibilityResearchReportMapper.getFeasibilityByUnitId(unitId);
    }

    @Override
    public List<StudyReport> getAppendixBuProjectId(int projectId){
        return studyReportMapper.getAppendixBuProjectId(projectId);
    }

    @Override
    public List<FeasibilityProjectName> getAllApprovedFeasibility(){
        return feasibilityResearchReportMapper.getAllApprovedFeasibility();
    }
}
