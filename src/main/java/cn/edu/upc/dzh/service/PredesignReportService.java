package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.mo.PredesignReportMo;
import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.vo.PredesignReportVo;
import cn.edu.upc.manage.vo.PredesignReportWithProject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    void insertReport(PredesignReportVo predesignReportVo);

    /**
     * 根据项目id获取初步设计
     * @param projectId
     * @return
     */
    PredesignReportMo getByProjectId(int projectId);

    /**
     * 添加招标备案
     * @param predesignReport
     */
    void insertKeepRecord(PredesignReport predesignReport);

    /**
     * 审批
     * @param predesignReport
     */
    void updateKeepApprove(PredesignReport predesignReport);

    /**
     * 根据项目id获取备案
     * @param projectId
     * @return
     */
    PredesignReport getKeepByProjectId(int projectId);

    /**
     * 删除标段
     * @param sectionId
     * @return
     */
    void deleteSection(int sectionId);

    /**
     * 修改初步设计
     * @param predesignReportVo
     * @return
     */
    void updateReport( PredesignReportVo predesignReportVo);
}
