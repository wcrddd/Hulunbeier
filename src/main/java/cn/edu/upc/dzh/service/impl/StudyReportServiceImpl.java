package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.StudyReportService;
import cn.edu.upc.manage.dao.StudyReportMapper;
import cn.edu.upc.manage.model.StudyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyReportServiceImpl implements StudyReportService {
    @Autowired
    private StudyReportMapper studyReportMapper;

    @Transactional
    @Override
    public void insertStudyReport(StudyReport studyReport){
        studyReportMapper.insertSelective(studyReport);
    }
}
