package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.TenderInformation;

public class TenderInformationVo extends TenderInformation {


    /**
     * <h3>manage</h3>
     * <p>带项目名称的招标信息</p>
     *
     * @author : gsl
     * @date : 2020-06-29 11:30
     **/
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
