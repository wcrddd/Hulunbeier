package cn.edu.upc.dzh.filter;

import cn.edu.upc.dzh.until.exception.BusinessException;
import cn.edu.upc.dzh.until.exception.EmBusinessError;
import cn.edu.upc.manage.model.User;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CrosFilter implements Filter{
    private FilterConfig config = null;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
    @Override
    public void destroy() {
        this.config = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws BusinessException, IOException, ServletException {
//        System.out.println("CrosFilter");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        String url=request.getServletPath();

        if(user!=null) {
            System.out.println(user.getUserName()+":"+url);
        } else {
            System.out.println(url);
        }

        // 允许哪些Origin发起跨域请求,nginx下正常
        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
        response.setHeader( "Access-Control-Allow-Origin", request.getHeader("Origin") );
        // 允许请求的方法
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader( "Access-Control-Max-Age", "3600" );
        // 表明它允许跨域请求包含xxx头
        response.setHeader( "Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept" );
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        // response.setHeader( "Access-Control-Expose-Headers", "*" );

        System.out.println(request.getHeader("Origin"));
        System.out.println("方法："+request.getMethod());
        System.out.println(request.getHeader("Access-Control-Request-Headers"));

        if (request.getMethod().equals( "OPTIONS" )) {
            response.setStatus( 200 );
            return ;
        }
        else if(request.getMethod().equals( "POST" )){
            if(!url.equals("/web/login")&&!url.equals("/web/logout")
                    &&!url.equals("/web/sendCode")&&!url.equals("/web/codeMaching")
                    &&!url.equals("/web/register")&&!url.equals("/index.jsp")
                    &&!url.equals("/post/selectPost")&&!url.equals("/unit/getUnit")
                    &&!url.equals("/web/selectusername")&&!url.equals("/guide/getOption")
                    &&!url.equals("/guide/importExcel")
                    &&user==null){
                System.out.println("请登录");

                response.addHeader("Access-Control-Expose-Headers","Access-Control-Max-Age,Content-Type");
//                response.setHeader( "Access-Control-Max-Age", "3000" );

//            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type","text/html;charset=UTF-8");
//            response.setHeader("Refresh","6; http://101.201.239.28:9080/hl/index.html#/");



            }

            else{
                filterChain.doFilter( servletRequest, response );
            }
        }
        else{
            filterChain.doFilter( servletRequest, response );
        }

    }

}
