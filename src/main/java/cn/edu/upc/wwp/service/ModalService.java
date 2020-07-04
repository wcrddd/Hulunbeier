package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.Modal;

import java.util.List;

public interface ModalService {

    public List<Modal> selectModal();
    public void updateModal(Modal recordUp);
    public void insertModal(Modal recordIn);
    public void deleteFlag(Modal recordDel);
}
