package com.baqn.test;

import com.bdqn.service.MailService;
import com.bdqn.util.Config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class TestMail {

    private Logger logger = LoggerFactory.getLogger(TestMail.class);
private MailService service = new MailService();

    @Test
    public void testPrint(){
        List<File> list = Config.files;
        logger.info(Config.mailHost);
        for (File file :list) {
            logger.info(file.getName());
        }
    }

    @Test
    public void testSend(){
        service.sendMail();
    }
}
