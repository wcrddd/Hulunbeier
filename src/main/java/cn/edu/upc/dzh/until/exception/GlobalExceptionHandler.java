package cn.edu.upc.dzh.until.exception;

import cn.edu.upc.manage.common.CommonReturnType;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 吃掉不可预知的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        Map<String,Object> responseData = new HashMap<>();
        if(e instanceof BusinessException){
            BusinessException businessException =(BusinessException)e;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }
        else if(e instanceof UnauthorizedException){
            UnauthorizedException unauthorizedException=(UnauthorizedException) e;
            responseData.put("errCode","10009");
            responseData.put("errMsg","您没有 "+unauthorizedException.toString().substring(unauthorizedException.toString().lastIndexOf("["))+"权限");
        }
        else if(e instanceof UnauthenticatedException)
        {
            responseData.put("errCode","10004");
            responseData.put("errMsg","您还没登录，请登录");
        }
        else if(e instanceof HttpMessageNotReadableException){
            responseData.put("errCode","10005");
            responseData.put("errMsg","数据格式有误");
        }
        else if(e instanceof BadSqlGrammarException){
            responseData.put("errCode","10006");
            responseData.put("errMsg","SQL语句有误");
            responseData.put("SQL",((BadSqlGrammarException) e).getSQLException());
        }
        else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
