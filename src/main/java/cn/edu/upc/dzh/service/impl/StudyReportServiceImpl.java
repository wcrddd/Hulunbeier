package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.dao.FeasibilityResearchReportMapper;
import cn.edu.upc.manage.dao.StudyReportMapper;
import cn.edu.upc.manage.model.FeasibilityResearchReport;
import cn.edu.upc.manage.model.StudyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyReportServiceImpl implements StudyReportService {
    @Autowired
    private StudyReportMapper studyReportMapper;
    @Autowired
    private FeasibilityResearchReportMapper feasibilityResearchReportMapper;

    @Transactional
    @Override
    public void insertAppendix(StudyReport studyReport){
        studyReportMapper.insertSelective(studyReport);
    }

    @Transactional
    @Override
    public void insertReport(FeasibilityResearchReport feasibilityResearchReport){
        feasibilityResearchReportMapper.insertSelective(feasibilityResearchReport);
    }
}
