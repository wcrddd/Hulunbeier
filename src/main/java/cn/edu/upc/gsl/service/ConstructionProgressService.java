package cn.edu.upc.gsl.service;

import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.vo.ConstructionProgressVo;

import java.util.List;

/**
 * 建设进度信息
 * @author gsl
 * @date 2020
 */
public interface ConstructionProgressService {
    /**
     * 新增建设单位信息
     * @param constructionProgress
     */
    void insert(ConstructionProgress constructionProgress);

    /**
     * 修改
     * @param constructionProgress
     */
    void update(ConstructionProgress constructionProgress);

    List<ConstructionProgress> select(Integer projectId,Integer contractId);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 以数组形式取出
     * @param projectId
     * @param contractId
     * @return
     */
    ConstructionProgressVo selectArray(Integer projectId, Integer contractId);
}
