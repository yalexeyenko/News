package com.epam.yalexeyenko.model;

import org.joda.time.LocalDate;

public class News extends BaseEntity {
	private String newsTitle;
	private LocalDate date;
	private String brief;
	private String content;
	
	public News() {
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String title) {
		this.newsTitle = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
