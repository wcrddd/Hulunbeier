package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.User;

/**
 * @author 董志涵
 */
public class UserUnitName extends User {
    private String nameUnit;

    private String postName;

    public String getNameUnit(){return nameUnit;}

    public void setNameUnit(String nameUnit){this.nameUnit=nameUnit;}

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
