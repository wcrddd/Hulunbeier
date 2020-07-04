package cn.edu.upc.dzh.until;

import cn.edu.upc.manage.model.User;

import javax.servlet.http.HttpSession;

public class SysUser {
    public static int getCurrentUserUnitId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getUserType() == 2) {
            return user.getDepartmentUnitId();
        } else {
            return 0;
        }
    }

    public static int getCurrentUserRole(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user.getRoleId();
    }
}
