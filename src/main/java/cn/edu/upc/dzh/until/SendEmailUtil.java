package cn.edu.upc.dzh.until;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmailUtil {
    public static boolean sendEMail(String tomail,String code) {
        //log.info("電子郵件接口執行開始！");
        String from = "2270892679@qq.com";
        String pwd = "hiicvtdnpqkadjeg";
//        String to = String.valueOf(map.get("mail"));
//        String subject = String.valueOf(map.get("subject"));
//        String content = String.valueOf(map.get("content"));
        String smtpHost = "smtp.qq.com";

//        if(StringUtils.isBlank(to) || StringUtils.isBlank(subject) || StringUtils.isBlank(content)){
//           // log.info("郵件地址（"+to+"）、主題（"+subject+"）、內容（"+content+"）有為空項，不符合發送條件");
//            return false;
//        }

        try{
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", smtpHost); // 发件人的邮箱的 SMTP服务器地址
            props.setProperty("mail.smtp.auth", "true"); // 请求认证，参数名称与具体实现有关

            // 创建Session实例对象
            Session session = Session.getDefaultInstance(props);
            // 创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            message.setFrom(new InternetAddress(from,"验证码发送系统", "UTF-8"));
            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tomail));
            // 设置发送日期
            message.setSentDate(new Date());
            // 设置邮件主题
            //message.setSubject(subject);
            message.setSubject("验证码", "UTF-8");
            // 设置纯文本内容的邮件正文
            //message.setText(content);
            message.setContent("您好，您的验证码是："+code+"。", "text/html;charset=UTF-8");
            // 保存并生成最终的邮件内容
            message.saveChanges();
            // 设置为debug模式, 可以查看详细的发送 log
            session.setDebug(true);
            // 获取Transport对象
            Transport transport = session.getTransport("smtp");
            // 第2个参数需要填写的是QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
            transport.connect(from, pwd);
            // 发送，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }catch(Exception e){

            return false;
        }

        return true;
    }
}
