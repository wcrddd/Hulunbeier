package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideUnitRelation;
import cn.edu.upc.manage.vo.GuideTitleA;
import cn.edu.upc.manage.vo.GuideUnitVo;
import cn.edu.upc.manage.vo.GuideVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GuideService2 {
    public void insertGuide(GuideUnitVo guideUnitVo);
    public void inserGuideUnitRelation(GuideUnitRelation guideUnitRelation);
    public List<Guide> getGuideByUnitId(int unitId);
    public List<Guide> selectGuide(int unitId,String title,String documentId);
    public void deleteGuide(int guideId);
    public List<Guide> getAllGuide();
    public List<Guide> selectAllGuide(String title,String documentId);
    GuideVo importExcel(MultipartFile file) throws IOException;
    List<GuideTitleA> getOption(int projectId);
}
