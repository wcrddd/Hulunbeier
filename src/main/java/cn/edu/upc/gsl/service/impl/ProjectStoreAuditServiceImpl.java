package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.dzh.until.ChineseCharToEn;
import cn.edu.upc.dzh.until.GetMessageCode;
import cn.edu.upc.dzh.until.SysUser;
import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.gsl.service.ProjectStoreAuditService;
import cn.edu.upc.manage.dao.ConstructionUnitMapper;
import cn.edu.upc.manage.dao.ProjectEvaluateMapper;
import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.ProjectYearPlanMapper;
import cn.edu.upc.manage.mo.ApproveStatistic;
import cn.edu.upc.manage.mo.ConstructUnitStatisticMo;
import cn.edu.upc.manage.mo.NumStatisticsMo;
import cn.edu.upc.manage.model.ProjectEvaluate;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.ProjectYearPlan;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.vo.ProjectStoreFlagVo;
import cn.edu.upc.manage.vo.ProjectStoreVo;
import cn.edu.upc.manage.vo.ProjectYearPlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gsl
 * @date 2020/5/19
 */
@Service
public class ProjectStoreAuditServiceImpl implements ProjectStoreAuditService {

    @Autowired
    private ProjectStoreMapper projectStoreMapper;
    @Autowired
    private ConstructionUnitMapper constructionUnitMapper;
    @Autowired
    private ProjectYearPlanMapper projectYearPlanMapper;
    @Autowired
    private ProjectEvaluateMapper projectEvaluateMapper;

    /**
     * 新增项目申报
     *
     * @param projectStore
     */
    @Override
    public int addProject(ProjectStore projectStore, HttpSession session, HttpServletRequest httpServletRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(new Date());
        String token = httpServletRequest.getHeader("token");
        int unitId= SysUser.getCurrentUserUnitId2(session,token);

//        int unitId = 6;
        String unitName = "";
        String unitCode = "";
        String projectCode = "";
        if (unitId == 0){
            unitName = "项目部";
            unitCode = "001";
        }else {
            unitName = constructionUnitMapper.getUnitNameById(unitId);
            unitCode = constructionUnitMapper.selectByPrimaryKey(unitId).getCode();
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumIntegerDigits(3);
        numberFormat.setMinimumIntegerDigits(3);

        int typeCode = projectStore.getProjectType();
        if (typeCode == 31){
            projectCode = year + unitCode + "01" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }else if (typeCode == 2){
            projectCode = year + unitCode + "02" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }else if (typeCode == 3){
            projectCode = year + unitCode + "03" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }else if (typeCode == 4){
            projectCode = year + unitCode + "04" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }else if (typeCode == 5){
            projectCode = year + unitCode + "05" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }else if (typeCode == 35){
            projectCode = year + unitCode + "06" + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        }

//        String projectCode = year + unitCode + ChineseCharToEn.getAllFirstLetter(projectStore.getProjectTypeName()) + numberFormat.format(projectStoreMapper.countByUnitId(unitId));
        System.out.println("项目编号：" + projectCode);
        projectStore.setConstruUnitId(unitId);
        projectStore.setConstrutUnitName(unitName);
        projectStore.setProjectId(projectCode);
//        if(projectStore.getInvestEstimate() <= 50){
//            projectStore.setPlanedFlag(2);
//        }
//        else
        if (projectStore.getInvestEstimate() >= 500){
            projectStore.setImportantFlag(0);
        }
        return projectStoreMapper.insertSelective(projectStore);
    }

    /**
     * 查询项目
     *
     * @param projectName
     * @param buildYear
     * @return
     */
    @Override
    public List<ProjectStore> getProject(String projectName, String buildYear) {
        System.out.println("projectName:"+projectName+" buildYear:"+buildYear);
        List<ProjectStore> projectStoreList;
        //当没有传参时和当传参为空时，都返回全部项目
        if (projectName == null || buildYear == null) {
            projectStoreList = projectStoreMapper.selectAllProject();
        } else if ("".equals(projectName) && "".equals(buildYear)) {
            projectStoreList = projectStoreMapper.selectAllProject();
        } else if ((!"".equals(projectName)) && "".equals(buildYear)) {
            //只根据项目名称查询
            projectStoreList = projectStoreMapper.selectByProjectName(projectName);
        } else if (!"".equals(projectName)) {
            //根据两个参数查
            projectStoreList = projectStoreMapper.selectByProjectNameAndBuildYear(projectName, buildYear);
        } else {
            //只根据年份查
            projectStoreList = projectStoreMapper.selectByBuildYear(buildYear);
        }
        return projectStoreList;
    }

    /**
     * 查询项目
     *
     * @param projectName
     * @param buildYear
     * @return
     */
    @Override
    public List<ProjectStore> getProject2(String projectName, String buildYear,int unitId) {
        System.out.println("projectName:"+projectName+" buildYear:"+buildYear);
        List<ProjectStore> projectStoreList;
        //当没有传参时和当传参为空时，都返回全部项目
        if (projectName == null || buildYear == null) {
            projectStoreList = projectStoreMapper.selectAllProject2(unitId);
        } else if ("".equals(projectName) && "".equals(buildYear)) {
            projectStoreList = projectStoreMapper.selectAllProject2(unitId);
        } else if ((!"".equals(projectName)) && "".equals(buildYear)) {
            //只根据项目名称查询
            projectStoreList = projectStoreMapper.selectByProjectName2(projectName,unitId);
        } else if (!"".equals(projectName)) {
            //根据两个参数查
            projectStoreList = projectStoreMapper.selectByProjectNameAndBuildYear2(projectName, buildYear,unitId);
        } else {
            //只根据年份查
            projectStoreList = projectStoreMapper.selectByBuildYear2(buildYear,unitId);
        }
        return projectStoreList;
    }

    /**
     * 跟新项目状态
     * @param record
     */
    @Override
    public void updateState(ProjectStore record) {
        projectStoreMapper.updateByPrimaryKeySelective(record);

    }

    /**
     * 改为只显示审批通过的
     * @return
     */
    @Override
    public List<ProjectStore> selectProjectPass() {
        return  projectStoreMapper.selectProjectPass();
    }

    /**
     * 根据项目id查询
     * @param id
     * @return
     */
    @Override
    public ProjectStore selectProjectById(Integer id) {
//        return projectStoreMapper.selectByPrimaryKey(id);
        return projectStoreMapper.selectById(id);
    }

    /**
     * 更新项目
     * @param projectStore
     */
    @Override
    public void update(ProjectStore projectStore) {
        projectStore.setApprove(0);
        projectStore.setStoreFlag(0);
        projectStoreMapper.updateByPrimaryKeySelective(projectStore);
    }

    /**
     * 区分已计划申报的和未进行计划申报的项目
     *
     * @param httpServletRequest
     * @return
     */
    @Override
    public List<ProjectStoreVo> divideProjectPlan(HttpServletRequest httpServletRequest) {
        //获取session
        //User user = (User) httpServletRequest.getSession().getAttribute("user");
        //Integer departmentUnitId = user.getDepartmentUnitId();

        Integer departmentUnitId = 1;
        List<ProjectStoreVo> allPassedProject= projectStoreMapper.selectPassProjectByUnitId(departmentUnitId);
        List<ProjectStoreVo> projectPlaned = projectStoreMapper.selectProjectPlaned(departmentUnitId);
        System.out.println(allPassedProject.containsAll(projectPlaned));
        for (ProjectStoreVo projectStoreVoPlan : projectPlaned){
            //如果查出来有，则设置标志位为0，不可进行申报

           for(ProjectStoreVo projectStoreVoPass : allPassedProject){
               Boolean b = (projectStoreVoPlan.getId()).equals(projectStoreVoPass.getId());
               System.out.println(b);
               if((projectStoreVoPlan.getId()).equals(projectStoreVoPass.getId())){
                   projectStoreVoPass.setCanPlan(0);
               }else {
                   projectStoreVoPass.setCanPlan(1);
               }
            }
        }
        return allPassedProject;
    }

    /**
     * 设置计划申报的标志位
     *
     * @param id
     * @param planedFlag
     */
    @Override
    public void setPlanedFlag(Integer id,Integer planedFlag) {
        projectStoreMapper.updatePlanedFlag(id,planedFlag,1);
    }

    /**
     *
     * @param projectStore 主要是三个参数
     * id ,approve, opinion
     * 0 未审核，1一级库 ,2二级库
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStoreFlag( ProjectStore projectStore){
        projectStoreMapper.updateStoreFlag(projectStore);
        if (projectStore.getStoreFlag() != 99){
            GetMessageCode.sendMessageToLeader(projectStore, "申报完成");
        }
    }

    /**
     * 挑选当年计划的项目
     * @param projectYearPlanVo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void selectYearPlan(ProjectYearPlanVo projectYearPlanVo){
        String projectName = "";
        List<ProjectYearPlan> projectYearPlanList = projectYearPlanVo.getProjectYearPlanList();
        for (ProjectYearPlan item:projectYearPlanList
             ) {
            if(projectYearPlanMapper.getByProjectId(item.getProjectId()) > 0){
                projectName = projectName + item.getProjectName()+"，";
            }
        }
        if (!projectName.equals("")){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,projectName+"已经加入到建设计划");
        }else {
            projectYearPlanMapper.insertList(projectYearPlanList);
        }
    }

    /**
     * 挑选当年计划的项目
     * @param projectYearPlan
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void selectYearPlan2(ProjectYearPlan projectYearPlan){
        if (projectStoreMapper.selectByPrimaryKey(projectYearPlan.getProjectId()).getInvestEstimate() <= 50){
            projectStoreMapper.updatePlanedFlag(projectYearPlan.getProjectId(), 2,3);
        }
        projectYearPlanMapper.insertSelective(projectYearPlan);
    }



    @Override
    public void deleteYearPlanProject(int projectId){
        projectYearPlanMapper.deleteYearPlanProject(projectId);
    }

    /**
     * 二级单位浏览本单位的全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    @Override
    public List<ProjectStore> getByUnitId(ProjectStoreFlagVo projectStoreFlagVo, int unitId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(new Date());
        projectStoreFlagVo.setThisYear(year);
        return projectStoreMapper.getByUnitId(projectStoreFlagVo, unitId);
    }

    /**
     * 二级单位浏览本单位的全部项目
     * storeFlag = 1传一级库，storeFlag = 2传二级库，storeFlag = 99传回收站，
     * @param projectStoreFlagVo
     * @return
     */
    @Override
    public List<ProjectStore> getAll(ProjectStoreFlagVo projectStoreFlagVo, User user){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(new Date());
        projectStoreFlagVo.setThisYear(year);
        System.out.println("user:"+user);
        if (user == null){
            User user1 = new User();
            user1.setUserType(0);
            user1.setDepartmentUnitId(0);
            return projectStoreMapper.getAll(projectStoreFlagVo,user1);
        }else {
            return projectStoreMapper.getAll(projectStoreFlagVo,user);
        }

    }

    /**
     * 获取合同显示界面
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    @Override
    public List<ProjectStore> getByUnitIdConstract(ProjectStoreFlagVo projectStoreFlagVo, int unitId){
        return projectStoreMapper.getByUnitIdConstract(projectStoreFlagVo,unitId);
    }


    /**
     * 获取竣工界面显示
     * @param projectStoreFlagVo
     * @param unitId
     * @return
     */
    @Override
    public List<ProjectStore> getByUnitIdFinish(ProjectStoreFlagVo projectStoreFlagVo, int unitId){
        return projectStoreMapper.getByUnitIdFinish(projectStoreFlagVo, unitId);
    }

    /**
     * 设置重点项目
     * @param projectId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateImportant( int projectId){
        projectStoreMapper.updateImportant(projectId);
    }

    /**
     * 更新项目建议
     * @param projectStore
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSuggestion(ProjectStore projectStore){
        projectStoreMapper.updateSuggestion(projectStore);
    }

    /**
     * 获取全部项目的数量统计
     * @return
     */
    @Override
    public NumStatisticsMo getNumStatistics(){
        return projectStoreMapper.getNumStatistics();
    }

    @Override
    public List<ProjectStore> getProjectLocation(){
        return projectStoreMapper.getProjectLocation();
    }

    @Override
    public List<ConstructUnitStatisticMo> getConstructUnitStatistic(){
        return projectStoreMapper.getConstructUnitStatistic();
    }

    /**
     * 获取部分项目的坐标和部分信息（地图显示）
     * @param flag
     * @return
     */
    @Override
    public List<ProjectStore> getProjectLocationClass(int flag){
        return projectStoreMapper.getProjectLocationClass(flag);
    }

    /**
     * 新增评价
     * @param projectEvaluate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEvaluate(ProjectEvaluate projectEvaluate){
        projectEvaluateMapper.insertSelective(projectEvaluate);
    }

    /**
     * 获取评价
     * @param projectId
     * @return
     */
    @Override
    public ProjectEvaluate getEvaluate(int projectId){
        return projectEvaluateMapper.getEvaluate(projectId);
    }

    /**
     * 获取审批的统计
     * @param departmentUnitId
     * @return
     */
    @Override
    public ApproveStatistic getApproveStatistics(int departmentUnitId){
        return projectStoreMapper.getApproveStatistics(departmentUnitId);
    }

    /**
     * 删除一个项目
     * @param id
     * @return
     */
    @Override
    public void delete(int id){
        projectStoreMapper.delete(id);
    }

    /**
     * 每日（下午两点）给手机发送审批信息
     */
    @Override
    public void sendApproveMessage(){
        ApproveStatistic approveStatistic = new ApproveStatistic();
        for (int i = 1; i < 8; i++){
            approveStatistic = projectStoreMapper.getApproveStatistics(i);
            if (approveStatistic.getStudyReportNum() > 0 || approveStatistic.getPredesignReportNum() > 0 ||
                approveStatistic.getKeepRecordNum() > 0 || approveStatistic.getStartReportNum() > 0 ||
                    approveStatistic.getAffixReportNum() > 0
            ){
                GetMessageCode.sendMessage2(i);
            }
        }
        approveStatistic = projectStoreMapper.getApproveStatistics(1);
        if (approveStatistic.getProjectReportNum() > 0){
            GetMessageCode.sendMessage2(40);
        }else if (approveStatistic.getContractNum() > 0){
            GetMessageCode.sendMessage2(41);
        }else if (approveStatistic.getTenderNum() > 0){
            GetMessageCode.sendMessage2(42);
        }

    }
}
