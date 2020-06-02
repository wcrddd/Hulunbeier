package cn.edu.upc.manage.common;

import java.util.List;
/**
 * <h3>manage</h3>
 * <p>分页工具类</p>
 *
 * @author : gsl
 * @date : 2020-05-19 15:37
 **/
public class PageUtils {

    /**
     * 当前页
     */
    private Integer pageNum;
    //每页的条数
    private Integer pageSize;

    //数据库查询得到
    //总条数
    private Integer total;
    //查询结果
    private List list;

    //计算得到
    //总页数
    private Integer pages;
    //上一页
    private Integer prePage;
    //下一页
    private Integer nextPage;

    public PageUtils(Integer pageNum, Integer pageSize, List list, Integer total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        this.list = list;
        this.total = total;

        this.pages = Double.valueOf(Math.ceil(total * 1.0 / pageSize)).intValue();
        this.prePage = this.pageNum == 1 ? this.pages : pageNum - 1;
        this.nextPage = this.pageNum.equals(this.pages) ? 1 : this.pageNum + 1;
    }

}
