package cn.edu.upc.manage.vo;

public class ConstructionProgressVo {

    /**
     * <h3>manage</h3>
     * <p>建设进度数组形式</p>
     * @author : gsl
     * @date : 2020-07-05 10:58
     **/

    /**
     * 记录时间
     */
    private String[] reportTime;
    /**
     * 花费金额
     */
    private String[] expendMoney;
    /**
     * 进度
     */
    private String[] progress;


    public String[] getReportTime() {
        return reportTime;
    }

    public void setReportTime(String[] reportTime) {
        this.reportTime = reportTime;
    }

    public String[] getExpendMoney() {
        return expendMoney;
    }

    public void setExpendMoney(String[] expendMoney) {
        this.expendMoney = expendMoney;
    }

    public String[] getProgress() {
        return progress;
    }

    public void setProgress(String[] progress) {
        this.progress = progress;
    }


}
