package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.Affix;

import java.util.List;

public interface AffixService {

    public List<Affix> selectAffix();
    public void updateAffix(Affix recordUp);
    public void insertAffix(Affix recordIn);
    public void deleteFlag(Affix recordDel);
    public List<Affix> getAffixByContractId(int contractId);

    /**
     * 审批
     * @param affix
     */
    void updateApprove(Affix affix);

    /**
     * 获取全部
     * @return
     */
    List<Affix> getAllReport();

    /**
     * 根据项目id获取验收报告
     * @param projectId
     * @return
     */
    List<Affix> getByProjectId(int projectId);

}
