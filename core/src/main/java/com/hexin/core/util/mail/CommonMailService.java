package com.hexin.core.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.hexin.core.util.properties.Property;

/**
 * 普通类型邮件发送,功能内部无法使用该方法
 * 下面是配置文件内容
 * 
mail.smtp.host = stmp.163.com
mail.smtp.port = 25
mail.smtp.auth = true
mail.smtp.user = myhexin163@163.com
mail.smtp.password = 369258147
mail.debug = true

mail.proxySet = true
mail.socksProxyHost = 192.168.0.1
mail.socksProxyPort = 80

mail.toUser = huqitao@myhexin.com,aaaa@aa.com
 * @author Administrator
 *
 */
public class CommonMailService {
	private static Log logger = LogFactory.getLog(CommonMailService.class);
	private static Properties mailPro = new Properties();
	private static Executor executor = Executors.newFixedThreadPool(10);

	static {
		//初始化，读取属性文件的过程
		try {
			mailPro.setProperty("proxySet", Property.getProperty("mail.proxySet"));
			mailPro.setProperty("socksProxyHost", Property.getProperty("mail.socksProxyHost"));
			mailPro.setProperty("socksProxyPort", Property.getProperty("mail.socksProxyPort"));
			mailPro.put("mail.smtp.auth", Property.getProperty("mail.smtp.auth"));
			mailPro.put("mail.smtp.host", Property.getProperty("mail.smtp.host"));
			mailPro.put("mail.smtp.port", Property.getProperty("mail.smtp.port"));
			mailPro.put("mail.smtp.user", Property.getProperty("mail.smtp.user"));
			mailPro.put("mail.smtp.password", Property.getProperty("mail.smtp.password"));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		} finally {
			
		}

	}

	public boolean sendMail(final CommonMail mail) {
		if(mail == null){
			return false;
		}
		//创建邮件发送任务
		Runnable task = new Runnable() {
			@Override
			public void run() {
				final String username = mailPro.getProperty("mail.smtp.user");
				final String password = mailPro.getProperty("mail.smtp.password");
				//创建发送邮件的会话
				Session session = Session.getDefaultInstance(mailPro, new Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}
						});
				session.setDebug(Boolean.parseBoolean(Property.getProperty("mail.debug")));
				
			    try {
			    	//创建邮件消息
			    	MimeMessage msg = new MimeMessage(session);
			    	//设置邮件发送人
					msg.setFrom(new InternetAddress(StringUtils.isEmpty(mail
							.getSender()) ? mailPro
							.getProperty("mail.smtp.user") : mail
							.getSender()));
					//分别设置邮件的收件人、抄送人和密送人
				    msg.setRecipients(Message.RecipientType.TO, strListToInternetAddresses(mail.getRecipientsTo()));
				    msg.setRecipients(Message.RecipientType.CC, strListToInternetAddresses(mail.getRecipientsCc()));
				    msg.setRecipients(Message.RecipientType.BCC, strListToInternetAddresses(mail.getRecipientsBcc()));
				    //设置邮件主题
				    msg.setSubject(mail.getSubject());
				    
				    Multipart mp = new MimeMultipart();
				    
				    //创建邮件主体内容
				    MimeBodyPart mbp1 = new MimeBodyPart();
				    mbp1.setText(mail.getBody());
				    mp.addBodyPart(mbp1);
				    
				    if(!CollectionUtils.isEmpty(mail.getAttachments())){
				    	//循环添加邮件附件
				    	MimeBodyPart attach = null;
				    	for(String path : mail.getAttachments()){
				    		attach = new MimeBodyPart();
				    	    try {
				    	    	attach.attachFile(path);
				    	    	mp.addBodyPart(attach);
							} catch (IOException e) {
								if (logger.isErrorEnabled()) {
									logger.error(e);
								}
							}

				    	}
				    }
				    
				    msg.setContent(mp);
				    msg.setSentDate(new Date());
				    
				    //邮件开始发送
				    Transport.send(msg);
				} catch (AddressException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				} catch (MessagingException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			    
				
			}

		};
		//使用Executor框架的线程池执行邮件发送任务
		executor.execute(task);
		return true;
	}
	
	/**
	 * 将列表中的字符串转换成InternetAddress对象
	 * @param list 邮件字符串地址列表
	 * @return InternetAddress对象数组
	 */
	private InternetAddress[] strListToInternetAddresses(List<String> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		int size = list.size();
		InternetAddress[] arr = new InternetAddress[size];
		for (int i = 0; i < size; i++) {
			try {
				arr[i] = new InternetAddress(list.get(i));
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

}