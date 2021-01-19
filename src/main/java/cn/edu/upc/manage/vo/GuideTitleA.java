package cn.edu.upc.manage.vo;

import java.util.List;

/**
 * @author 董志涵 2020-11-25
 */
public class GuideTitleA {
//    private String titleA;
//
//    private List<GuideTitleB> titleBList;
//
//    public String getTitleA() {
//        return titleA;
//    }
//
//    public void setTitleA(String titleA) {
//        this.titleA = titleA;
//    }
//
//    public List<GuideTitleB> getTitleBList() {
//        return titleBList;
//    }
//
//    public void setTitleBList(List<GuideTitleB> titleBList) {
//        this.titleBList = titleBList;
//    }
    private String title;

    private List<GuideTitleB> children;

    private boolean disabled = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GuideTitleB> getChildren() {
        return children;
    }

    public void setChildren(List<GuideTitleB> children) {
        this.children = children;
    }


    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
