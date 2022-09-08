package net.koreate.deploy.notice.model;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeBoard {
	
	private int notice_num;
	private String notice_category;
	private String notice_author;
	private String notice_title;
	private String notice_content;
	private Date notice_date;
	
}
