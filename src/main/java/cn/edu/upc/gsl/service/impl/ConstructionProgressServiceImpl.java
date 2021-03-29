package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ConstructionProgressService;
import cn.edu.upc.manage.dao.ConstructionProgressAppendixMapper;
import cn.edu.upc.manage.dao.ConstructionProgressMapper;
import cn.edu.upc.manage.dao.ProjectSectionMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.mo.ConstructionProgressMo;
import cn.edu.upc.manage.mo.SectionProgressMo;
import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.model.ConstructionProgressAppendix;
import cn.edu.upc.manage.model.ProjectSection;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.vo.ConstructionProgressParamVo;
import cn.edu.upc.manage.vo.ConstructionProgressVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 施工进程信息
 *
 * @author gsl
 * @date 2020/6/30
 */
@Service
public class ConstructionProgressServiceImpl implements ConstructionProgressService {

    @Autowired
    private ConstructionProgressMapper constructionProgressMapper;
    @Autowired
    private ConstructionProgressAppendixMapper constructionProgressAppendixMapper;
    @Autowired
    private ProjectSectionMapper projectSectionMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;

    /**
     * @param constructionProgressParamVo
     */
    @Override
    public void insert(ConstructionProgressParamVo constructionProgressParamVo) {
        ConstructionProgress constructionProgress = new ConstructionProgress();
        BeanUtils.copyProperties(constructionProgressParamVo,constructionProgress);
        List<ConstructionProgressAppendix> constructionProgressAppendixList = constructionProgressParamVo.getConstructionProgressAppendixList();
        constructionProgressMapper.insertSelective(constructionProgress);
        if (constructionProgressAppendixList.size() > 0){
            int progressId = constructionProgress.getId();
            for (ConstructionProgressAppendix iem:constructionProgressAppendixList
            ) {
                iem.setProgressId(progressId);
            }
            constructionProgressAppendixMapper.insertList(constructionProgressAppendixList);
        }
        projectStoreMapper.updatePlanedFlag(constructionProgressParamVo.getProjectId(),19,19);
//        if (constructionProgress.getProgress().equals("100")){
        if (constructionProgress.getProgress() == 100){
            projectStoreMapper.updateSectionFinishNum(constructionProgressParamVo.getProjectId());
        }

    }

    /**
     * 修改
     *
     * @param constructionProgress
     */
    @Override
    public void update(ConstructionProgress constructionProgress) {
        constructionProgressMapper.updateByPrimaryKeySelective(constructionProgress);
    }

    /**
     * 根据项目id和合同id查询施工进度
     * @param projectId
     * @param contractId
     * @return
     */
    @Override
    public List<ConstructionProgress> select(Integer projectId, Integer contractId) {
        return constructionProgressMapper.select(projectId, contractId);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        constructionProgressMapper.updateDelFlag(id);
    }

    /**
     * 以数组形式取出
     * gsl
     * @param projectId 项目id
     * @param contractId 合同id
     * @return
     */
    @Override
    public ConstructionProgressVo selectArray(Integer projectId, Integer contractId) {
        //从数据库取出数据
        List<ConstructionProgress> constructionProgressList = constructionProgressMapper.select(projectId, contractId);
        ConstructionProgressVo constructionProgressVo = new ConstructionProgressVo();
        //建立List，以接收数据
        List<String> reportTimeList = new ArrayList<>();
        List<Float> expendMoneyList = new ArrayList<>();
        List<Integer> progressList = new ArrayList<>();
        //通过循环，把想要的值取出来
        for (ConstructionProgress constructionProgress : constructionProgressList) {
//            reportTimeList.add(constructionProgress.getReportTime());
            expendMoneyList.add(constructionProgress.getExpendMoney());
            progressList.add(constructionProgress.getProgress());
        }

        //将集合转换为数组
        String[] reportTime = new String[reportTimeList.size()];
        reportTimeList.toArray(reportTime);
        String[] expendMoney = new String[expendMoneyList.size()];
        expendMoneyList.toArray(expendMoney);
        String[] progress = new String[progressList.size()];
        progressList.toArray(progress);

        //设置返回信息
        constructionProgressVo.setReportTime(reportTime);
        constructionProgressVo.setExpendMoney(expendMoney);
        constructionProgressVo.setProgress(progress);

        return constructionProgressVo;
    }

    @Override
    public List<SectionProgressMo> getByProjectId(int projectId){
        List<SectionProgressMo> sectionProgressMoList = new ArrayList<>();
        List<ProjectSection> projectSectionList = projectSectionMapper.getSectionByProjectId(projectId);
        for (ProjectSection p1:projectSectionList
             ) {
            int sectionId = p1.getId();
            List<ConstructionProgress> constructionProgressList = constructionProgressMapper.getBySectionId(sectionId);
            SectionProgressMo sectionProgressMo = new SectionProgressMo();
            sectionProgressMo.setSectionId(sectionId);
            sectionProgressMo.setSectionName(p1.getSectionName());
            sectionProgressMo.setProjectId(p1.getProjectId());
            List<ConstructionProgressMo> constructionProgressMoList = new ArrayList<>();
            for (ConstructionProgress p2:constructionProgressList
                 ) {

                int progressId = p2.getId();
                List<ConstructionProgressAppendix> constructionProgressAppendixList = constructionProgressAppendixMapper.getByProgressId(progressId);
                ConstructionProgressMo constructionProgressMo = new ConstructionProgressMo();
                constructionProgressMo.setConstructionProgressAppendixList(constructionProgressAppendixList);
//                constructionProgressMo.setProgress(p2.getProgress());
                BeanUtils.copyProperties(p2,constructionProgressMo);
                constructionProgressMoList.add(constructionProgressMo);
            }
            sectionProgressMo.setConstructionProgressMoList(constructionProgressMoList);
            sectionProgressMoList.add(sectionProgressMo);
        }
        return sectionProgressMoList;
    }
}
