package cn.edu.upc.dzh.until;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 董志涵
 */
public class GetIp {
        public static String saveUrl ="/Users/weixj/Desktop/hlb/";
//    public static String saveUrl ="/home/pm-application/tomcat/webapps/hl/";
    /**
     * 获取登录用户IP地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "localhost";
        }
        return ip;
    }


}
