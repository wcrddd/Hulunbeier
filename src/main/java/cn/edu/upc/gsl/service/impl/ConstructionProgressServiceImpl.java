package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.ConstructionProgressService;
import cn.edu.upc.manage.dao.ConstructionProgressMapper;
import cn.edu.upc.manage.model.ConstructionProgress;
import cn.edu.upc.manage.vo.ConstructionProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 施工进程信息
 *
 * @author gsl
 * @date 2020/6/30
 */
@Service
public class ConstructionProgressServiceImpl implements ConstructionProgressService {

    @Autowired
    private ConstructionProgressMapper constructionProgressMapper;

    /**
     * 新增建设单位信息
     * insert和 insertSelective的区别在于
     * 前者是会把所有的值插入，及时没有传进来
     * 后者会判空，只把传进来的值插入
     *
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

    /**
     * 根据项目id和合同id查询施工进度
     * @param projectId
     * @param contractId
     * @return
     */
    @Override
    public List<ConstructionProgress> select(Integer projectId, Integer contractId) {
        return constructionProgressMapper.select(projectId, contractId);
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

    /**
     * 以数组形式取出
     * gsl
     * @param projectId 项目id
     * @param contractId 合同id
     * @return
     */
    @Override
    public ConstructionProgressVo selectArray(Integer projectId, Integer contractId) {
        //从数据库取出数据
        List<ConstructionProgress> constructionProgressList = constructionProgressMapper.select(projectId, contractId);
        ConstructionProgressVo constructionProgressVo = new ConstructionProgressVo();
        //建立List，以接收数据
        List<String> reportTimeList = new ArrayList<>();
        List<String> expendMoneyList = new ArrayList<>();
        List<String> progressList = new ArrayList<>();
        //通过循环，把想要的值取出来
        for (ConstructionProgress constructionProgress : constructionProgressList) {
            reportTimeList.add(constructionProgress.getReportTime());
            expendMoneyList.add(constructionProgress.getExpendMoney());
            progressList.add(constructionProgress.getProgress());
        }

        //将集合转换为数组
        String[] reportTime = new String[reportTimeList.size()];
        reportTimeList.toArray(reportTime);
        String[] expendMoney = new String[expendMoneyList.size()];
        expendMoneyList.toArray(expendMoney);
        String[] progress = new String[progressList.size()];
        progressList.toArray(progress);

        //设置返回信息
        constructionProgressVo.setReportTime(reportTime);
        constructionProgressVo.setExpendMoney(expendMoney);
        constructionProgressVo.setProgress(progress);

        return constructionProgressVo;
    }
}
