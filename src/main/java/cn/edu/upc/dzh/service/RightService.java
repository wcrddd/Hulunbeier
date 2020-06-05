package cn.edu.upc.dzh.service;

import cn.edu.upc.manage.model.Right;

import java.util.List;

public interface RightService {
    public void insertRight(Right right);
    public List<Right> getAllRight();
    public void updateRight(Right right);
    public void deleteRight(int rightId);


}
