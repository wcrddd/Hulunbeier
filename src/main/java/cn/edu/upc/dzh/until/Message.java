package cn.edu.upc.dzh.until;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class Message {
    public static void send(String tel,String projectName){
        System.out.println("发送短信");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GDgumi6VGuBcU5m6J7J", "9Irsb0pWgBHWgj37ucVkkkD4z6zmsD");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", "17554269640");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "ABC项目");
        request.putQueryParameter("TemplateCode", "SMS_205891685");
        request.putQueryParameter("TemplateParam", "{\"code\":\"1234\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
