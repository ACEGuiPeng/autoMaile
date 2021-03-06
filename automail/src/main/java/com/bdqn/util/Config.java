package com.bdqn.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/7/3.
 */
public final class Config {

    private static final ResourceBundle config = ResourceBundle.getBundle("properties/sendMail");

    //邮箱服务器
    public static final String mailHost = config.getString("mail.smtp.host");

    //身份验证
    public static final String auth = config.getString("mail.smtp.auth");

    //协议
    public static final String protocol = config.getString("mail.transport.protocol");
    //发件人邮件地址
    public static final String sendMailAccount = config.getString("sendMailAccount");
    //发件人邮件授权码
    public static final String sendMailPassword = config.getString("sendMailPassword");
    //收件人地址
    public static final String receiveMailAccount = config.getString("receiveMailAccount");
    //邮箱服务器端口
    public static final String smtpPort = config.getString("mail.smtp.port");
    //备份文件根目录
    private static String backupFilePath = config.getString("backupFilePath");
    //备份文件
    public static final List<File> files = getFiles(backupFilePath);

    private static List<File> getFiles(String backupFilePath) {
        List<File> files = new ArrayList<File>();
        Map<String, String> fileNameAndPaths = new HashMap<String, String>(); //文件上级路径
        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();//当前日期
        String timePattern = "\\D*_[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";//日期正则
        //得到多个文件根目录
        String[] filePaths = backupFilePath.trim().split(",");
        for (String filepath : filePaths) {
            fileNameAndPaths.put(filepath.trim().substring(0, filepath.lastIndexOf("/")) + "/",
                    filepath.trim().substring(filepath.lastIndexOf("/") + 1, filepath.indexOf("_")));
        }
        //遍历文件夹下的文件
        Set<String> parentPaths = fileNameAndPaths.keySet();
        for (String parentPath : parentPaths) {
            File file = new File(parentPath);
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                //判断是不是这个文件名
                if (Pattern.matches(".*" + fileNameAndPaths.get(parentPath) + ".*", fileList[i].getName())) {
                    //判断是不是最新的日期
                    if (Pattern.matches(".*" + currentTime + ".*", fileList[i].getName())) {
                        files.add(fileList[i]);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return files;
    }
}
