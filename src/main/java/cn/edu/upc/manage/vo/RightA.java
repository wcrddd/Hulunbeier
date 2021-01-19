package cn.edu.upc.manage.vo;

import java.util.List;

/**
 * @author 董志涵
 */
public class RightA {
    private int value;

    private String label;

    private List<RightB> children;

    private int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<RightB> getChildren() {
        return children;
    }

    public void setChildren(List<RightB> children) {
        this.children = children;
    }


}
