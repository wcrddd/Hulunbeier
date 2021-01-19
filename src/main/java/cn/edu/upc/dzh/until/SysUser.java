package cn.edu.upc.dzh.until;

import cn.edu.upc.manage.model.User;

import javax.servlet.http.HttpServletRequest;
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

    public static User getCurrentUser(HttpSession session, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        System.out.println(token);
        User user = (User) session.getAttribute(token);
        return user;
    }


    public static int getCurrentUserUnitId2(HttpSession session,String token) {
        User user = (User) session.getAttribute(token);
        if (user.getUserType() == 2) {
            return user.getDepartmentUnitId();
        } else {
            return 0;
        }
    }

    public static int getCurrentUserRole(HttpSession session, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        System.out.println(token);
        User user = (User) session.getAttribute(token);
        return user.getRoleId();
    }

    public static String getUsername(HttpSession session, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        User user = (User) session.getAttribute(token);
        return user.getUserName();
    }

    public static int getUserType(HttpSession session, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        User user = (User) session.getAttribute(token);
        return user.getUserType();
    }
}
