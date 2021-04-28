package cn.edu.upc.dzh.until;

import cn.edu.upc.manage.dao.ProjectStoreMapper;
import cn.edu.upc.manage.dao.UserMapper;
import cn.edu.upc.manage.model.ProjectStore;
import cn.edu.upc.manage.model.User;
import cn.edu.upc.manage.model.UserWithUnitName;
import cn.edu.upc.manage.vo.UserUnitName;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

@Component
public class GetMessageCode {
//    private static final String QUERY_PATH = "https://openapi.danmi.com/distributor/sendSMS";
//    private static final String ACCOUNT_SID = "8f36163bf06a5087afa11bd41dc520d9";
//    private static final String AUTH_TOKEN = "09505f6fe39574703df56dd8f0a83ed4";
    private static final String QUERY_PATH = "https://openapi.danmi.com/distributor/sendSMS";
    private static final String ACCOUNT_SID = "044406fd831f39b18bd307485311f00f";
    private static final String AUTH_TOKEN = "f3c4bb4bcfedef4b7eb002b5d79809f7";
//    @Autowired
//    private UserMapper userMapper;
    private static GetMessageCode getMessageCode;
    private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private static ProjectStoreMapper projectStoreMapper = SpringContextHolder.getBean(ProjectStoreMapper.class);

//    public static void sendMessage(int projectId){
//        ProjectStore projectStore = projectStoreMapper.selectByPrimaryKey(projectId);
//        String phoneNumber = "";
//        String url = "http://39.99.43.201:8084/hl/index.html#/sys/message/home?projectId="+projectStore.getId();
//        User user = new User();
//        if (projectStore.getImportantFlag() == 0){
//            user = userMapper.getByDepartment(1,1);
//        }else if (projectStore.getProjectNature() == "企业自筹资金"){
//            user = userMapper.getByDepartment(2,1);
//        }else if (projectStore.getProjectType() == 31){
//            user = userMapper.getByDepartment(3,1);
//        }else if (projectStore.getProjectType() == 2){
//            user = userMapper.getByDepartment(4,1);
//        }else if (projectStore.getProjectType() == 3){
//            user = userMapper.getByDepartment(5,1);
//        }else if (projectStore.getProjectType() == 4){
//            user = userMapper.getByDepartment(6,1);
//        }else if (projectStore.getProjectType() == 5){
//            user = userMapper.getByDepartment(7,1);
//        }
//        phoneNumber = user.getTelephone();
//        getCode(phoneNumber, url);
//    }

    public static void main(String[] args){
//        ProjectStore projectStore = new ProjectStore();
//        projectStore.setConstrutUnitName("苏沁农牧场");
//        projectStore.setProjectName("测试项目");
//        projectStore.setPlace("呼伦贝尔");
//        projectStore.setInvestEstimate((float) 100);
//        sendMessageToLeader(projectStore, "申报");
//        String phoneNumber = "19854258756";
//        String url = "http://39.99.43.201:8084/MobileFarm/index.html#/?departmentUnitId=" + 1;
//        String tamp = " 【亿科翰网络科技】有项目需要您审批，请点击链接 " + url + " 进行审批退订回T";
//        String param = url;
//        String templateid = "274255";
//        getCode(phoneNumber, tamp, param, templateid);
    }

    public static void sendMessage2(int departmentUnitId){
        System.out.println(departmentUnitId);
        String phoneNumber = "";
        String url = "http://39.99.43.201:8084/MobileFarm/index.html#/?departmentUnitId=" + departmentUnitId;
        User user = userMapper.getByDepartment(departmentUnitId,1);
        phoneNumber = user.getTelephone();
        String tamp = " 【亿科翰网络科技】有项目需要您审批，请点击链接 " + url + " 进行审批退订回T";
        String param = url;
        String templateid = "274255";
        getCode(phoneNumber, tamp, param, templateid);
    }

    public static void sendMessageToLeader(ProjectStore projectStore, String stage){

        String phoneNumber = "13061491890";
        String tamp = " 【呼伦贝尔农垦集团项目管理系统】" + projectStore.getConstrutUnitName() +
                " 申报的 " + projectStore.getProjectName() + " 项目已经进行到 " + stage + " 阶段，建设地点为 "
                + projectStore.getPlace() + " ，投资预算为 " + projectStore.getInvestEstimate() + "万元";
        String param = projectStore.getConstrutUnitName() +
                "," + projectStore.getProjectName() + "," + stage + ","
                + projectStore.getPlace() + "," + projectStore.getInvestEstimate();
        String templateid = "274286";
        getCode(phoneNumber, tamp, param, templateid);
    }

    // 根据相应的手机号发送验证码
    public static JSONObject getCode(String phone, String tamp, String param, String templateid) {
        JSONObject res=new JSONObject();
//        String urlLink = "http://39.99.43.201:8084/hl/index.html#/sys/message/home?projectId=160";
//        System.out.println("验证码："+rod);
        String timestamp = getTimestamp();
        String sig = getMD5(ACCOUNT_SID, AUTH_TOKEN, timestamp);
        OutputStreamWriter out = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            //通过网络请求类，来发送
            URL url = new URL(QUERY_PATH);
            //访问QUERY_PATH前，先打开它
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");//设置打开方式为post
            connection.setDoInput(true);//设置是否允许数据写入
            connection.setDoOutput(true);//设置是否允许参数输出
            connection.setConnectTimeout(5000);//设置连接响应时间
            connection.setReadTimeout(10000);//设置参数读取时间
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//设置请求头信息

            //提交请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            String args = getQueryArgs(ACCOUNT_SID, tamp, phone, timestamp, sig, "JSON");
            String args = getQueryArgs2(ACCOUNT_SID, templateid, phone, timestamp, sig, "JSON",param);
            out.write(args);//写入参数
            out.flush();//刷新

            //读取返回结果
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String temp = "";
            while((temp = br.readLine())!= null){
                result.append(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //转化得到的json类型返回结果，并获取其中的respCode的值
        JSONObject json =  JSONObject.parseObject(result.toString());
        System.out.println(json);
        String respCode = json.getString("respCode");
        System.out.println("respCode:"+respCode);
        //成功发送验证码以后，获取的状态码为0000
        String defuatRespCode = "0000";
        JSONArray json2=json.getJSONArray("failList");
        if(json2.size()>0){
            JSONObject json3 = json2.getJSONObject(0);
            res.put("errorCode",json3.getString("respCode"));
            return res;
        }
        if(defuatRespCode.equals(respCode)){
            res.put("code","success");
            return res;
        }else{
            res.put("errorCode",respCode);
            return res;
        }
    }
    /**参数拼接
     * @param accountSid
     * @param smsContent
     * @param to
     * @param sig
     * @param timestamp
     * @param respDataType
     * @return
     */
    public static String getQueryArgs(String accountSid,String smsContent,String to,String timestamp,String sig,String respDataType){
        return "accountSid=" +accountSid + "&smsContent="
                +smsContent + "&to=" +to +"&timestamp="
                +timestamp +"&sig=" +sig +"&respDataType="+respDataType;
    }

    public static String getQueryArgs2(String accountSid,String templateid,String to,String timestamp,String sig,String respDataType,String param){
        return "accountSid=" +accountSid + "&templateid="
                +templateid + "&to=" +to +"&timestamp="
                +timestamp +"&sig=" +sig +"&respDataType="+respDataType + "&param=" + param;
    }

    /**
     * 获取时间戳
     * @return
     */
    public static String getTimestamp(){

        // 时间戳
        long timestamp = System.currentTimeMillis();
        return timestamp + "";
    }

    /**
     * MD5加密,得到sig签名
     * @param sid
     * @param token
     * @param timestamp
     * @return
     */
    public static String getMD5(String sid,String token,String timestamp){
        //StringBuffer线程不安全。
        StringBuilder result = new StringBuilder();
        String source = sid +token +timestamp;
        try {
            //MessageDigest.getInstance("MD5")获取MD5的实例
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //获得需要加密的字符数组
            byte[] bytes = digest.digest(source.getBytes());
            for(byte b : bytes){
                //把字节转化为16进制
                String hex = Integer.toHexString(b&0xff);
                if(hex.length()==1){
                    result.append("0"+hex);
                }else{
                    result.append(hex);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.toString();
    }
    /**
     * 创建验证码
     * @return
     */
    public static String smsCode(){
        //创建一个6位的随机数字
        String ran = new Random().nextInt(1000000)+"";
        if(ran.length()!=6){
            smsCode();
        }
        return ran;
    }


    public static void setGetMessageCode(GetMessageCode getMessageCode) {
        GetMessageCode.getMessageCode = getMessageCode;
    }
}
