package cn.edu.upc.manage.common;

/**
 * @author zcc
 * @date 2019/3/25 20:13
 */
//返回通用格式
public class CommonReturnType {
    private String status;//success或fail
    private int code;
    private String msg;

    private Object data;

    public static CommonReturnType create(Object result){

        return CommonReturnType.create(result,"success",0,"操作成功");
    }
    public static CommonReturnType create(Object result,String msg){
        return CommonReturnType.create(result,"success",0,msg);
    }
    public static CommonReturnType create(Object result,String status,int code,String msg){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setCode(code);
        type.setMsg(msg);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
