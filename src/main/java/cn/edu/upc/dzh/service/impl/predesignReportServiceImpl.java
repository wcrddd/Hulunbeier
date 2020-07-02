package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.manage.dao.PredesignReportAppendixMapper;
import cn.edu.upc.manage.dao.PredesignReportMapper;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.PredesignReport;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import cn.edu.upc.manage.vo.PredesignReportWithProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class predesignReportServiceImpl implements PredesignReportService {
    @Autowired
    private PredesignReportAppendixMapper predesignReportAppendixMapper;
    @Autowired
    private PredesignReportMapper predesignReportMapper;

    @Transactional
    @Override
    public void insertAppendix(PredesignReportAppendix predesignReportAppendix){
        int projectId=predesignReportAppendix.getProjectId();
        PredesignReport predesignReport=new PredesignReport();
        predesignReport.setProjectId(projectId);
        if(predesignReportMapper.selectByProjectId(projectId)==null)
        {predesignReportMapper.insertSelective(predesignReport);}
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

    @Transactional
    @Override
    public void updateApproveExamine(PredesignReport predesignReport){
        predesignReportMapper.updateByPrimaryKeySelective(predesignReport);
    }

    @Override
    public List<PredesignReportWithProject> getAllpredesignReport(){
        return predesignReportMapper.getAllpredesignReport();
    }


}
