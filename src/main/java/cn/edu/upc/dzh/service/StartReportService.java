package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.StartReport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 董志涵2020-11-29
 */
public interface StartReportService {
    /**
     * 新增
     * @param startReport
     */
    void addReport( StartReport startReport);

    /**
     * 审批
     * @param startReport
     */
    void updateApprove( StartReport startReport);

    /**
     * 修改
     * @param startReport
     */
    void updateReport( StartReport startReport);

    /**
     * 获取全部开工报告
     * @return
     */
    List<StartReport> getAllReport();

    /**
     * 根据项目id获取开工报告
     * @param projectId
     * @return
     */
    StartReport getByProjectId(int projectId);
}
