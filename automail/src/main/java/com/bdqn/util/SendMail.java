package com.bdqn.util;


import com.bdqn.entity.MailAuthenticator;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * Created by guipeng on 2017/7/5.
 */
public class SendMail {

    public static Session getSession() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.socketFactory.port", Config.smtpPort);
        properties.setProperty("mail.transport.protocol", Config.protocol);   // 使用的协议（JavaMail规范要求）
        properties.setProperty("mail.smtp.host", Config.mailHost);   // 发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.auth", Config.auth);            // 需要请求认证
        properties.setProperty("mail.smtp.port", Config.smtpPort);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        MailAuthenticator mailAuthenticator = new MailAuthenticator(Config.sendMailAccount,Config.sendMailPassword);
        Session session = Session.getDefaultInstance(properties,mailAuthenticator);
        return session;
    }
    public static int sendMailOut( Message message) {
        try {
            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = getSession();
            /*session.setDebug(true);    */                             // 设置为debug模式, 可以查看详细的发送 log
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(Config.sendMailAccount, Config.sendMailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            if (transport!=null){
                transport.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
