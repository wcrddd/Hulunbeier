package cn.edu.upc.wwp.service;

import cn.edu.upc.manage.model.Affix;

import java.util.List;

public interface AffixService {

    public List<Affix> selectAffix();
    public void updateAffix(Affix recordUp);
    public void insertAffix(Affix recordIn);
    public void deleteFlag(Affix recordDel);
    public List<Affix> getAffixByContractId(int contractId);

}
