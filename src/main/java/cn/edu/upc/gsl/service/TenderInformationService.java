package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.TenderInformation;

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
     */
    void deleteTender(Integer id);

    TenderInformation select(Integer id);

    public List<TenderInformation> getAllTender();
}
