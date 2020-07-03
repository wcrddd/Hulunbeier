package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.ModalMapper;
import cn.edu.upc.manage.model.Modal;

import cn.edu.upc.wwp.service.ModalService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service("modalService")

public class ModalServiceImpl implements ModalService {

    @Resource
    ModalMapper modalMapper;

    @Override
    public List<Modal> selectModal() {
        return modalMapper.selectModal();
    }

    @Override
    public void updateModal(Modal recordUp) {
        recordUp.setOperator("test");
        modalMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public void insertModal(Modal recordIn) {
        recordIn.setOperator("test");
        modalMapper.insertSelective(recordIn);
    }

    @Override
    public void deleteFlag(Modal recordDel) {
        Modal result=modalMapper.selectByPrimaryKey(recordDel.getId());
        if(result!=null){
            recordDel.setDelFlag(1);
            modalMapper.updateByPrimaryKeySelective(recordDel);
        }
    }

}
