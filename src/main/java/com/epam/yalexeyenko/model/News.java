package com.epam.yalexeyenko.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.epam.yalexeyenko.converter.LocalDateAttributeConverter;

@Entity
@Table(name = "BLOCKNEWS")
public class News extends BaseEntity {
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "NEWSDATE")
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate date;
	
	@Column(name = "BRIEF")
	private String brief;
	
	@Column(name = "NEWSCONTENT")
	private String content;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID", insertable=false, updatable=false)
	@ManyToOne
	@JoinColumn(name = "STATUS_ID")
	private Status status;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID", insertable=false, updatable=false)
	@ManyToOne()
	@JoinColumn(name = "USER_ID")
	private User user;

	public News() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
