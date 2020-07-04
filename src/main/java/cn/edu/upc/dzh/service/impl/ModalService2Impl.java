package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.ModalService2;
import cn.edu.upc.manage.dao.ModalMapper;
import cn.edu.upc.manage.model.Modal;
import cn.edu.upc.manage.vo.ModalProjectName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 董志涵
 */
@Service
public class ModalService2Impl implements ModalService2 {
    @Autowired
    private ModalMapper modalMapper;

    @Override
    public void updateState(Modal modal){
        modalMapper.updateByPrimaryKeySelective(modal);
    }

    @Override
    public List<ModalProjectName> getAllModal(){
        return modalMapper.getAllModal();
    }

    @Override
    public List<Modal> getModalByContractId(int contractId){
        return modalMapper.getModalByContractId(contractId);
    }
}
