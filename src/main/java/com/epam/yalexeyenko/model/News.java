package com.epam.yalexeyenko.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "BLOCKNEWS")
@Table(name = "BLOCKNEWS")
public class News extends BaseEntity {
	@NotNull(message = "NotNull.news.title")
	@Size(min = 3, max = 100, message = "Size.news.title")
	@Column(name = "TITLE")
	private String title;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "NotNull.news.date")
	@Temporal(TemporalType.DATE)
	@Column(name = "NEWSDATE")
	private Date date;
	
	@NotNull(message = "NotNull.news.brief")
	@Size(min = 3, max = 1000, message = "Size.news.brief")
	@Column(name = "BRIEF")
	private String brief;
	
	@NotNull(message = "NotNull.news.content")
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
