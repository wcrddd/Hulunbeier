package cn.edu.upc.wwp.service.impl;

import cn.edu.upc.manage.dao.AffixMapper;
import cn.edu.upc.manage.model.Affix;
import cn.edu.upc.wwp.service.AffixService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("affixService")
public class AffixServiceImpl implements AffixService {

    @Resource
    AffixMapper affixMapper;

    @Override
    public List<Affix> selectAffix() {

        return affixMapper.selectAffix();
    }

    @Override
    public void updateAffix(Affix recordUp) {

        recordUp.setOperator("test");
        affixMapper.updateByPrimaryKeySelective(recordUp);
    }

    @Override
    public void insertAffix(Affix recordIn) {

        recordIn.setOperator("test");
        affixMapper.insertSelective(recordIn);
    }

    @Override
    public void deleteFlag(Affix recordDel) {
        Affix result=affixMapper.selectByPrimaryKey(recordDel.getId());
        if(result!=null){
            recordDel.setDelFlag(1);
            affixMapper.updateByPrimaryKeySelective(recordDel);
        }
    }
    }

