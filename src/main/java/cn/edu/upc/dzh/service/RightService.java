package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Rights;
import cn.edu.upc.manage.model.ViewRights;

import java.util.List;

public interface RightService {
    public void insertRight(Rights right);
    public List<ViewRights> getAllRight();
    public void updateRight(Rights right);
    public void deleteRight(int rightId);
    public List<ViewRights> selectByName(String name);
    public void insertRight2(Rights right);
    public List<ViewRights> selectRightByRole(int roleId);

}
