package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import cn.edu.upc.manage.model.ViewStudyReport;
import cn.edu.upc.manage.vo.FeasibilityProjectName;

import java.util.List;

public interface StudyReportService {
    public void insertAppendix(StudyReport studyReport);
    public void insertReport(FeasibilityResearchReport feasibilityResearchReport);
    public List<ViewStudyReport> getReportByProjectId(int projectId);
    public void deleteAppendix(StudyReport studyReport);
    public void updateReport(FeasibilityResearchReport feasibilityResearchReport);
    public void updateApproveExamine(FeasibilityResearchReport feasibilityResearchReport);
    public List<FeasibilityProjectName> getFeasibilityByUnitId(int unitId,String projectName);
    public List<StudyReport> getAppendixBuProjectId(int projectId);
    public List<FeasibilityProjectName> getAllApprovedFeasibility(String projectName);

    /**
     * 根据项目id
     * @param projectId
     * @return
     */
    FeasibilityResearchReport getByProjectId(Integer projectId);
    public void updateReport2(FeasibilityResearchReport feasibilityResearchReport);
}
