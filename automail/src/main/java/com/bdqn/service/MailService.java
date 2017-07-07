
package com.bdqn.service;

import com.bdqn.entity.Mail;
import com.bdqn.util.Config;
import com.bdqn.util.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件类，处理邮件
 * Created by guipeng on 2017/7/4.
 */
public class MailService {
    private Logger logger = LoggerFactory.getLogger(MailService.class);

    private Mail initMail() {
        Mail mail = new Mail(Config.sendMailAccount, Config.receiveMailAccount, "数据备份",
                "website数据库备份,oa数据库备份", Config.files);
        return mail;
    }
    public void sendMail(){
        int state = SendMail.sendMailOut(initMail().getMailMessage());
        if (state>0){
            logger.info("邮件发送成功!");
        }else{
            logger.info("邮件发送失败！");
        }
    }
}
