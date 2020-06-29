package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import cn.edu.upc.manage.model.ViewStudyReport;

import java.util.List;

public interface StudyReportService {
    public void insertAppendix(StudyReport studyReport);
    public void insertReport(FeasibilityResearchReport feasibilityResearchReport);
    public List<ViewStudyReport> getReportByProjectId(int projectId);
    public void deleteAppendix(StudyReport studyReport);
    public void updateReport(FeasibilityResearchReport feasibilityResearchReport);
}
