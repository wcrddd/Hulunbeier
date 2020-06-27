package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.PredesignReportService;
import cn.edu.upc.manage.dao.PredesignReportAppendixMapper;
import cn.edu.upc.manage.model.PredesignReportAppendix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class predesignReportServiceImpl implements PredesignReportService {
    @Autowired
    private PredesignReportAppendixMapper predesignReportAppendixMapper;

    @Transactional
    @Override
    public void insertAppendix(PredesignReportAppendix predesignReportAppendix){
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
}
