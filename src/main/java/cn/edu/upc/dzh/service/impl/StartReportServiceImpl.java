package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.StartReportService;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.StartReportMapper;
import cn.edu.upc.manage.model.StartReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 董志涵 2020-11-29
 */
@Service
public class StartReportServiceImpl implements StartReportService {
    @Autowired
    private StartReportMapper startReportMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;

    /**
     * 新增
     * @param startReport
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReport( StartReport startReport){
        startReportMapper.insertSelective(startReport);
        projectStoreMapper.updatePlanedFlag(startReport.getProjectId(),16,16);
    }

    /**
     * 审批
     * @param startReport
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApprove( StartReport startReport){
        startReport.setApproveTime(new Date());
        startReportMapper.updateApprove(startReport);

        int approve = startReport.getApprove();
        if (approve == 1){
            projectStoreMapper.updatePlanedFlag(startReport.getProjectId(),17,18);
        }else {
            projectStoreMapper.updatePlanedFlag(startReport.getProjectId(),18,17);
        }
    }

    /**
     * 修改
     * @param startReport
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReport( StartReport startReport){
        startReport.setApprove(0);
        startReportMapper.updateByPrimaryKeySelective(startReport);
        projectStoreMapper.updatePlanedFlag(startReport.getProjectId(),16,16);
    }

    /**
     * 获取全部
     * @return
     */
    @Override
    public List<StartReport> getAllReport(){
        List<StartReport> startReportList = startReportMapper.getAllReport();
        return startReportList;
    }

    /**
     * 根据项目id获取
     * @param projectId
     * @return
     */
    @Override
    public StartReport getByProjectId(int projectId){
        return startReportMapper.getByProjectId(projectId);
    }
}
