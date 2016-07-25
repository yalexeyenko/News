package com.epam.yalexeyenko.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BLOCKNEWS")
public class News extends BaseEntity {
	@NotBlank(message = "NotBlank.news.title")
	@Size(min = 3, max = 100, message = "Size.news.title")
	@Column(name = "TITLE")
	private String title;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "NotNull.news.date")
	@Column(name = "NEWSDATE")
	private Date date;
	
	@NotBlank(message = "NotBlank.news.brief")
	@Size(min = 3, max = 1000, message = "Size.news.brief")
	@Column(name = "BRIEF")
	private String brief;
	
	@NotBlank(message = "NotBlank.news.content")
	@Size(min = 3, max = 2000, message = "Size.news.content")
	@Column(name = "NEWSCONTENT")
	private String content;

	public News() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
