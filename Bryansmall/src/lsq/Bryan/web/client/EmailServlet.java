package lsq.Bryan.web.client;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServlet {
    private static String mail = "lsqbryan@163.com";
    private static String identify = "ZPWDEWBWQTYAUDID";
    public static void SendMail(String mail_target, String title, String content) {
        //设置服务器参数
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "465");

        //登录邮箱
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail,identify);
            }
        };

        //实例化session和邮件
        Session session = Session.getInstance(props,authenticator);
        MimeMessage mess = new MimeMessage(session);
        try {
            //设置邮件各项信息
            mess.setFrom(new InternetAddress(mail));
            mess.setRecipients(Message.RecipientType.TO, mail_target);
            mess.setSubject(title);
            mess.setContent(content,"text/html;charset=utf-8");
            Transport.send(mess); //发送邮件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
