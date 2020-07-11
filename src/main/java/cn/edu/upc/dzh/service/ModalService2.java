package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Modal;
import cn.edu.upc.manage.vo.ModalProjectName;

import java.util.List;

/**
 * @author 董志涵
 */
public interface ModalService2 {
    /**
     * 更新状态
     * @param modal
     */
    public void updateState(Modal modal);

    /**
     * 获取全部竣工填报
     * @return
     */
    public List<ModalProjectName> getAllModal();

    /**
     * 根据id获取竣工信息
     * @param contractId
     * @return
     */
    public Modal getModalByContractId(int contractId);
}
