package com.epam.yalexeyenko.model;

import java.util.Date;

public class News extends BaseEntity {
	private String newsTitle;
	private Date date;
	private String brief;
	private Content content;

	public News() {
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String title) {
		this.newsTitle = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
