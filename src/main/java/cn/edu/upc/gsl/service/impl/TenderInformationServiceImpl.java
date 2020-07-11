package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.TenderInformationService;
import cn.edu.upc.manage.dao.TenderInformationMapper;
import cn.edu.upc.manage.model.TenderInformation;
import cn.edu.upc.manage.vo.TenderInformationContractState;
import cn.edu.upc.manage.vo.TenderInformationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    TenderInformationMapper tenderInformationMapper;

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
     * @param tenderInformation
     */
    @Override
    public void updateTender(TenderInformation tenderInformation) {
        tenderInformation.setApprove(0);
        tenderInformationMapper.updateByPrimaryKeySelective(tenderInformation);
    }

    /**
     * 审核
     * @param tenderInformation
     */
    @Override
    public void updateTender2(TenderInformation tenderInformation) {
        tenderInformationMapper.updateByPrimaryKeySelective(tenderInformation);
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
     * 根据项目id查询招标
     * @param projectId
     * @return
     */
    @Override
    public List<TenderInformation> selectByProjectId(Integer projectId) {
        return tenderInformationMapper.selectByProjectId(projectId);
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
}
