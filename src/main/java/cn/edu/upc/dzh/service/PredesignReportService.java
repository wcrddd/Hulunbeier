package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.vo.PredesignReportWithProject;

import java.util.List;

public interface PredesignReportService {
    public void insertAppendix(PredesignReportAppendix predesignReportAppendix);
    public List<PredesignReportAppendix> getAppendixByProjectId(int projectId);
    public void updateAppendix(PredesignReportAppendix predesignReportAppendix);
    public void updateApproveExamine(PredesignReport predesignReport);
    public List<PredesignReportWithProject> getAllpredesignReport();
    public List<PredesignReportWithProject> getCanApproveByUnitId(int unitId);
    public List<PredesignReportWithProject> selectCanApprove(int unitId,String projectName);
    public List<PredesignReportWithProject> selectAllpredesignReport(String projectName);
}
