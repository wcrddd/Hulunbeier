package cn.edu.upc.manage.vo;

import java.util.List;

/**
 * @author 董志涵 2020-11-25
 */
public class GuideTitleB {
//    private String titleB;
//
//    private List<String> titleCList;
//
//    public String getTitleB() {
//        return titleB;
//    }
//
//    public void setTitleB(String titleB) {
//        this.titleB = titleB;
//    }
//
//    public List<String> getTitleCList() {
//        return titleCList;
//    }
//
//    public void setTitleCList(List<String> titleCList) {
//        this.titleCList = titleCList;
//    }
    private String title;

    private List<GuideTitleC> children;

    private boolean disabled = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GuideTitleC> getChildren() {
        return children;
    }

    public void setChildren(List<GuideTitleC> children) {
        this.children = children;
    }


    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
