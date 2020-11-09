package com.example.util;

import com.example.Entity.MailConfig;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件工具
 * @author AllenVan
 * @version 1.0
 * @date 2020/10/27
 */
@Component
public class MailUtil {
    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;

    public MailUtil(JavaMailSender mailSender , MailConfig mailConfig) {
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
    }

    /**
     * 普通邮件发送
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String subject, String content) {
        // new 一个简单邮件消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 和配置文件中的的username相同，相当于发送方
        message.setFrom(mailConfig.getFrom());
        // setCc 抄送，setTo 发送，setBCc密送
        message.setCc(mailConfig.getRecipient());
        message.setSubject("【类型】：【文本】-【来源】【" + mailConfig.getEnvironmental() + "】-主题【" + subject + "】");
        // 正文
        message.setText(content);
        // 发送
        mailSender.send(message);
    }

    /**
     * 发送HTML邮件
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String subject, String content) {

        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfig.getFrom());
            helper.setTo(mailConfig.getRecipient());
            helper.setSubject("【类型】:【HTML】【来源】-【" + mailConfig.getEnvironmental() + "】-主题【" + subject + "】");
            //true代表支持html
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // logger.error("发送HTML邮件失败：", e);
        }
    }

    /**
     * 发送带附件邮件
     * @param subject
     * @param content
     * @param file 附件
     */
    public void sendAttachmentMail(String subject, String content, File file) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(mailConfig.getFrom());
            helper.setTo(mailConfig.getRecipient());
            helper.setSubject("【类型】:【附件】-【来源】-【" + mailConfig.getEnvironmental() + "】-主题【" + subject + "】");
            helper.setText(content, true);
            FileSystemResource file1 = new FileSystemResource(file);
            String fileName = file1.getFilename();
            //添加附件，可多次调用该方法添加多个附件
            helper.addAttachment(fileName, file1);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带图片邮件
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String subject, String content, String rscPath, String rscId) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfig.getFrom());
            helper.setTo(mailConfig.getRecipient());
            helper.setSubject("【类型】:【图片】-【来源】-【" + mailConfig.getEnvironmental() + "】-主题【" + subject + "】");
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            //重复使用添加多个图片
            helper.addInline(rscId, res);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
