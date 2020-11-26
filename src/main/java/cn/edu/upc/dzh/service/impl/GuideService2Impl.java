package cn.edu.upc.dzh.service.impl;

import cn.edu.upc.dzh.service.GuideService2;
import cn.edu.upc.dzh.until.GetIp;
import cn.edu.upc.manage.common.CommonReturnType;
import cn.edu.upc.manage.dao.GuideMapper;
import cn.edu.upc.manage.dao.GuideOptionMapper;
import cn.edu.upc.manage.dao.GuideUnitRelationMapper;
import cn.edu.upc.manage.model.Guide;
import cn.edu.upc.manage.model.GuideOption;
import cn.edu.upc.manage.model.GuideUnitRelation;
import cn.edu.upc.manage.vo.GuideTitleA;
import cn.edu.upc.manage.vo.GuideTitleB;
import cn.edu.upc.manage.vo.GuideUnitVo;
import cn.edu.upc.manage.vo.GuideVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GuideService2Impl implements GuideService2 {
    @Autowired
    private GuideMapper guideMapper;
    @Autowired
    private GuideUnitRelationMapper guideUnitRelationMapper;
    @Autowired
    private GuideOptionMapper guideOptionMapper;

    @Transactional
    @Override
    public void insertGuide(GuideUnitVo guideUnitVo){
//         guideMapper.insertSelective(guide);
//         return guideMapper.selectLastInsert();
        Guide guide = new Guide();
        guide.setCreateTime(new Date());
        BeanUtils.copyProperties(guideUnitVo,guide);
        guideMapper.updateByPrimaryKeySelective(guide);
        Integer[] unitId = guideUnitVo.getUnitId();
        GuideUnitRelation guideUnitRelation = new GuideUnitRelation();
        guideUnitRelation.setGuideId(guideUnitVo.getId());
        for(Object uId: unitId){
            guideUnitRelation.setUnitId((Integer) uId);
            guideUnitRelationMapper.insertSelective(guideUnitRelation);
        }
    }

    @Transactional
    @Override
    public void inserGuideUnitRelation(GuideUnitRelation guideUnitRelation){
        guideUnitRelationMapper.insertSelective(guideUnitRelation);
    }

    @Override
    public List<Guide> getGuideByUnitId(int unitId){
        return guideMapper.getGuideByUnitId(unitId);
    }

    @Override
    public List<Guide> selectGuide(int unitId,String title,String documentId){
        return guideMapper.selectGuide(unitId,title,documentId);
    }

    @Transactional
    @Override
    public void deleteGuide(int guideId){
        guideMapper.deleteGuide(guideId);
    }

    @Override
    public List<Guide> getAllGuide(){
        return guideMapper.getAllGuide();
    }

    @Override
    public List<Guide> selectAllGuide(String title,String documentId){
        return guideMapper.selectAllGuideBy(title,documentId);
    }

    @Override
    @Transactional
    public GuideVo importExcel(MultipartFile file) throws IOException {
        Guide guide = new Guide();
        guideMapper.insert(guide);
//        Integer guideId = guideMapper.selectLastInsert();
        Integer guideId = guide.getId();
        System.out.println("guideId:"+guideId);
        List<GuideOption> guideOptionList = new ArrayList<>();
        String title1 = "";
        String title2 = "";
        String title3 = "";
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet0 = workbook.getSheetAt(0);
        int rowNumber=sheet0.getPhysicalNumberOfRows();

        String rowValue = "";
        double rowDouble = 0;
        System.out.println("rowNumber:"+rowNumber);
        for (int i = 1; i < rowNumber; i++){
            XSSFRow row = sheet0.getRow(i);
            CellType cellType = row.getCell(0).getCellType();
            if (cellType == CellType.NUMERIC){
                rowDouble = row.getCell(0).getNumericCellValue();
                rowValue = String.valueOf(rowDouble);

            }else if (cellType == CellType.STRING){
                rowValue = row.getCell(0).getStringCellValue();
            }
            if(rowValue.equals("一") || rowValue.equals("二") || rowValue.equals("三")){
                title1 = row.getCell(1).getStringCellValue();
            }

            else if (rowValue.contains("（")){
                title2 = row.getCell(1).getStringCellValue();
            }

            else if (rowDouble > 0){
                title3 = row.getCell(1).getStringCellValue();
                rowDouble = 0;
            }

            if (!title1.equals("") && !title2.equals("") && !title3.equals("")){
                System.out.println(title1 + "," + title2 + "," + title3);
                GuideOption guideOption = new GuideOption();
                guideOption.setGuideId(guideId);
                guideOption.setTitleA(title1);
                guideOption.setTitleB(title2);
                guideOption.setTitleC(title3);
                guideOptionList.add(guideOption);
                title3 = "";
            }

        }
        guideOptionMapper.insertList(guideOptionList);
        String type="guide";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String currentTime = dateFormat.format( now );

        String fileName=file.getOriginalFilename();
        System.out.println("源文件名："+fileName);
        File filed=new File(GetIp.saveUrl+currentTime+"/"+type);
        if(!filed.exists()){
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis()+(int)(1+Math.random()*1000)+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(),filename));
        System.out.println(currentTime+"/"+type+"/"+filename);
        GuideVo guideVo = new GuideVo();
        guideVo.setId(guideId);
        guideVo.setAppendix(currentTime+"/"+type+"/"+filename);
        return guideVo;
    }

    @Override
    public List<GuideTitleA> getOption(int projectId){
        List<GuideTitleA> guideTitleAList = new ArrayList<>();

        List<String> titleA = new ArrayList<>();
        titleA = guideOptionMapper.getTitleA(projectId);
        for (String s1:titleA
             ) {
            List<String> titleB = guideOptionMapper.getTitleB(projectId, s1);
            GuideTitleA guideTitleA = new GuideTitleA();
            guideTitleA.setTitleA(s1);
            List<GuideTitleB> guideTitleBList = new ArrayList<>();
            for (String s2:titleB
                 ) {
                List<String> titleC = guideOptionMapper.getTitleC(projectId, s1, s2);
                GuideTitleB guideTitleB = new GuideTitleB();
                guideTitleB.setTitleB(s2);
                guideTitleB.setTitleCList(titleC);
                guideTitleBList.add(guideTitleB);
            }
            guideTitleA.setTitleBList(guideTitleBList);
            guideTitleAList.add(guideTitleA);
        }
        return guideTitleAList;
    }
}
