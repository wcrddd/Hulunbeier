package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.TenderInformationService;
import cn.edu.upc.manage.dao.ProjectSectionMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.TenderInformationMapper;
import cn.edu.upc.manage.model.ProjectSection;
import cn.edu.upc.manage.model.TenderInformation;
import cn.edu.upc.manage.vo.ProjectSectionVo;
import cn.edu.upc.manage.vo.TenderInformationContractState;
import cn.edu.upc.manage.vo.TenderInformationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <h3>manage</h3>
 * <p>招标信息</p>
 * @author : gsl
 * @date : 2020-06-12 18:08
 **/
@Service
public class TenderInformationServiceImpl implements TenderInformationService {

    @Autowired
    private TenderInformationMapper tenderInformationMapper;
    @Autowired
    private ProjectSectionMapper projectSectionMapper;
    @Autowired
    private ProjectStoreMapper projectStoreMapper;

    /**
     * 新增招标信息
     * @param tenderInformation
     */
    @Override
    public void addTender(TenderInformation tenderInformation) {
        tenderInformationMapper.insertSelective(tenderInformation);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int deleteTender(Integer id) {
        //更新标志位
        return tenderInformationMapper.updateDelFlagForId(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public TenderInformation select(Integer id) {
       return tenderInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     *
     * @param projectSectionList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTender(List<ProjectSection> projectSectionList) {
//        tenderInformation.setApprove(0);
//        tenderInformationMapper.updateByPrimaryKeySelective(tenderInformation);
//        List<ProjectSection> projectSectionList = projectSectionVo.getProjectSectionList();
        for (ProjectSection item:projectSectionList
             ) {
            item.setTenderApprove(0);
        }
//        projectSection.setTenderApprove(0);
//        projectSectionMapper.updateByPrimaryKeySelective(projectSection);
        projectSectionMapper.updateTenderList(projectSectionList);
        projectStoreMapper.updatePlanedFlag(projectSectionList.get(0).getProjectId(),10);
    }

    /**
     * 审核
     * @param projectSection
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTender2(ProjectSection projectSection) {
        projectSection.setTenderApproveTime(new Date());
//        tenderInformationMapper.updateByPrimaryKeySelective(tenderInformation);
        projectSectionMapper.updateByPrimaryKeySelective(projectSection);
        int projectId = projectSection.getProjectId();
        int approve = projectSection.getTenderApprove();
        int sectionTenderNum = projectSectionMapper.count(projectId,2);
        int sectionTenderApproveNum = projectSectionMapper.count(projectId,3);
        if(sectionTenderApproveNum == sectionTenderNum){
//            if (approve == 1){
                projectStoreMapper.updatePlanedFlag(projectId,11);
//            }else {
//                projectStoreMapper.updatePlanedFlag(projectId,12);
//            }
        }else if(approve == 2){
            projectStoreMapper.updatePlanedFlag(projectId,12);
        }

    }

    /**
     * 获取所有招标信息
     * @return
     */
    @Override
    public List<TenderInformationVo> getAllTender(){
        return tenderInformationMapper.getAllTender();
    }

    /**
     * 根据项目id查询招标（改2020-12-01）
     * @param projectId
     * @return
     */
    @Override
    public List<ProjectSection> selectByProjectId(Integer projectId) {
        return projectSectionMapper.getTenderByProjectId(projectId);
    }

    /**
     * 根据项目id删除几条招标
     * @param projectId
     */
    @Override
    public void delTenderByProjectId(Integer projectId) {
        tenderInformationMapper.updateDelFlag(projectId);
    }

    /**
     * 根据项目id获取招标以及合同的状态
     * @param projectId
     * @return
     */
    @Override
    public List<TenderInformationContractState> getTenderContractState(int projectId){
        return tenderInformationMapper.getTenderContractState(projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTender2(List<ProjectSection> projectSectionList){
//        List<ProjectSection> projectSectionList = projectSectionVo.getProjectSectionList();
        for (ProjectSection item:projectSectionList
             ) {
            item.setTenderTime(new Date());
        }
//        projectSectionMapper.insertTender(projectSection);
//        projectSectionMapper.updateByPrimaryKeySelective(projectSection);
        projectSectionMapper.updateTenderList(projectSectionList);
        int projectId = projectSectionList.get(0).getProjectId();
        projectStoreMapper.updatePlanedFlag(projectId,10);
    }
}
