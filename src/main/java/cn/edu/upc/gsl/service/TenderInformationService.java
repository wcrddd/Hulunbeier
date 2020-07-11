package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.TenderInformation;
import cn.edu.upc.manage.vo.TenderInformationContractState;
import cn.edu.upc.manage.vo.TenderInformationVo;

import java.util.List;

public interface TenderInformationService {

    /**
     * 增加招标信息
     * @param tenderInformation
     * 接口里的方法一定是public 且抽象的
     * 所以不写public abstract 默认的就是
     */
    public abstract void addTender(TenderInformation tenderInformation);

    /**
     * 修改
     * @param tenderInformation
     */
    void updateTender(TenderInformation tenderInformation);

    /**
     * 删除(一般采用软删除)
     * 至del_flag不为0
     * @param id
     * @return
     */
    int deleteTender(Integer id);

    /**
     * 根据id
     * @param id
     * @return
     */
    TenderInformation select(Integer id);

    /**
     * 返回所有招标，带项目名称
     * @return
     */
    List<TenderInformationVo> getAllTender();

    /**
     * 根据项目id获取招标列表
     * @param projectId
     * @return
     */
    List<TenderInformation> selectByProjectId(Integer projectId);

    /**
     * 根据项目id删除一些招标信息
     * @param projectId
     */
    void delTenderByProjectId(Integer projectId);

    /**
     * 根据项目id获取招标以及合同的状态
     * @param projectId
     * @return
     */
    List<TenderInformationContractState> getTenderContractState(int projectId);

    /**
     * 审核
     * @param tenderInformation
     */
    void updateTender2(TenderInformation tenderInformation);
}
