package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ConstructionProgressService;
import cn.edu.upc.manage.dao.ConstructionProgressMapper;
import cn.edu.upc.manage.model.ConstructionProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 施工进程信息
 * @author gsl
 * @date 2020/6/30
 */
@Service
public class ConstructionProgressServiceImpl implements ConstructionProgressService {

    @Autowired
    private ConstructionProgressMapper constructionProgressMapper;

    /**
     * 新增建设单位信息
     *insert和 insertSelective的区别在于
     * 前者是会把所有的值插入，及时没有传进来
     * 后者会判空，只把传进来的值插入
     * @param constructionProgress
     */
    @Override
    public void insert(ConstructionProgress constructionProgress) {
        constructionProgressMapper.insertSelective(constructionProgress);
    }

    /**
     * 修改
     *
     * @param constructionProgress
     */
    @Override
    public void update(ConstructionProgress constructionProgress) {
        constructionProgressMapper.updateByPrimaryKeySelective(constructionProgress);
    }

    @Override
    public List<ConstructionProgress> select(Integer projectId,Integer contractId) {
        return constructionProgressMapper.select(projectId,contractId);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        constructionProgressMapper.updateDelFlag(id);
    }
}
