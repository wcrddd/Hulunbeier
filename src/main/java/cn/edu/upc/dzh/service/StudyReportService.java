package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;

public interface StudyReportService {
    public void insertAppendix(StudyReport studyReport);
    public void insertReport(FeasibilityResearchReport feasibilityResearchReport);

}
