package com.epam.yalexeyenko.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NEWSBLOCK")
public class News extends BaseEntity {
	@Column(name = "TITLE")
	private String newsTitle;
	@Temporal(TemporalType.DATE)
	@Column(name = "NEWSDATE")
	private Date date;
	@Column(name = "BRIEF")
	private String brief;
	@Column(name = "NEWSCONTENT")
	private String content;

	public News() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
