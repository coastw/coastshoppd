/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Coast
 */
public class EMail {

    public static void send(File file,String fileName) throws Exception {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.126.com");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);

        //主题
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("coastw@126.com"));
        msg.setRecipients(Message.RecipientType.TO, "coastshop@126.com");
        msg.setSubject("数据库备份");
        //msg.setText("你好");

        //个部分邮件内容
        //文本
        MimeBodyPart content = new MimeBodyPart();
        content.setContent("<h3>您好</h3><br /><p>请查看附件</p>", "text/html;charset=utf-8");
        //附件
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.setDataHandler(new DataHandler(new FileDataSource(file)));
        attachment.setFileName(MimeUtility.encodeText(fileName));

        //合并各个内容
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(content);
        mimeMultipart.addBodyPart(attachment);
        mimeMultipart.setSubType("mixed");

        //添加内容到邮件
        msg.setContent(mimeMultipart);

        //发送
        Transport transport = session.getTransport();
        transport.connect("coastw@126.com", "CoastSnow0309");
        transport.sendMessage(msg, msg.getAllRecipients());
    }
}
