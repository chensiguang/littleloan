package com.hexin.core.util.mail;

import java.util.List;

public class CommonMail {
	/**
	 * 发送人
	 */
	private String sender;
	/**
	 * 收件人
	 */
	private List<String> recipientsTo;
	/**
	 * 抄送人
	 */
	private List<String> recipientsCc;
	/**
	 * 密送人
	 */
	private List<String> recipientsBcc;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 正文
	 */
	private String body;
	/**
	 * 附件列表
	 */
	private List<String> attachments;
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<String> getRecipientsTo() {
		return recipientsTo;
	}
	public void setRecipientsTo(List<String> recipientsTo) {
		this.recipientsTo = recipientsTo;
	}
	public List<String> getRecipientsCc() {
		return recipientsCc;
	}
	public void setRecipientsCc(List<String> recipientsCc) {
		this.recipientsCc = recipientsCc;
	}
	public List<String> getRecipientsBcc() {
		return recipientsBcc;
	}
	public void setRecipientsBcc(List<String> recipientsBcc) {
		this.recipientsBcc = recipientsBcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
}