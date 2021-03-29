package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.dao.FeasibilityResearchReportMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.StudyReportMapper;
import cn.edu.upc.manage.dao.ViewStudyReportMapper;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.StudyReport;
import cn.edu.upc.manage.model.ViewStudyReport;
import cn.edu.upc.manage.vo.FeasibilityProjectName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StudyReportServiceImpl implements StudyReportService {
    @Autowired
    private StudyReportMapper studyReportMapper;
    @Autowired
    private FeasibilityResearchReportMapper feasibilityResearchReportMapper;
    @Autowired
    private ViewStudyReportMapper viewStudyReportMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;


    @Transactional
    @Override
    public void insertAppendix(StudyReport studyReport){
        studyReportMapper.insertSelective(studyReport);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertReport(StudyReport studyReport){
        studyReportMapper.insertSelective(studyReport);
        projectStoreMapper.updatePlanedFlag(studyReport.getProjectId(),1,1);
    }

    @Override
    public List<ViewStudyReport> getReportByProjectId(int projectId){
        return viewStudyReportMapper.getReportByProjectId(projectId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAppendix(StudyReport studyReport){
        studyReport.setDelFlag(studyReport.getId());
        studyReportMapper.updateByPrimaryKeySelective(studyReport);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReport(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReport.setApprove(0);
        feasibilityResearchReport.setExamine(0);
        feasibilityResearchReportMapper.updateReport(feasibilityResearchReport);
    }

    @Override
    public void updateReport2(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReportMapper.updateReport(feasibilityResearchReport);
    }

    @Override
    public void updateApproveExamine(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReportMapper.updateByPrimaryKeySelective(feasibilityResearchReport);
    }

    @Override
    public List<FeasibilityProjectName> getFeasibilityByUnitId(int unitId,String projectName){
        return feasibilityResearchReportMapper.getFeasibilityByUnitId(unitId,projectName);
    }

    @Override
    public List<StudyReport> getAppendixBuProjectId(int projectId){
        return studyReportMapper.getAppendixBuProjectId(projectId);
    }

    @Override
    public List<StudyReport> getAllApprovedFeasibility(StudyReport studyReport){
        return studyReportMapper.getAllFeasibility(studyReport);
    }

    /**
     * 根据项目id查询可研报告（改2020-12-01）
     *
     * @param projectId
     * @return
     */
    @Override
    public StudyReport getByProjectId(Integer projectId) {
        return studyReportMapper.getByProjectId(projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReport(StudyReport studyReport){
        studyReport.setApprove(0);
        studyReportMapper.updateByPrimaryKeySelective(studyReport);
        projectStoreMapper.updatePlanedFlag(studyReport.getProjectId(),1,1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApprove(StudyReport studyReport){
        studyReport.setApproveTime(new Date());
//        studyReportMapper.updateApprove(studyReport);
        studyReportMapper.updateByPrimaryKeySelective(studyReport);
        int approve = studyReport.getApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(studyReport.getProjectId(),2,3);
        }else {
            projectStoreMapper.updatePlanedFlag(studyReport.getProjectId(),3,2);
        }
    }

}
