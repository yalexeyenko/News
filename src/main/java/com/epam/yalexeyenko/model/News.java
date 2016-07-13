package com.epam.yalexeyenko.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "NEWSBLOCK")
@Table(name = "NEWSBLOCK")
@NamedQueries({
	@NamedQuery(name = "News.findAll", query = "SELECT n FROM NEWSBLOCK n"),
	@NamedQuery(name = "News.findAllOrderedByDate", query = "SELECT n FROM NEWSBLOCK n ORDER BY n.date DESC")
})

public class News extends BaseEntity {
	@Column(name = "TITLE")
	private String title;
	@Temporal(TemporalType.DATE)
	@Column(name = "NEWSDATE")
	private Date date;
	@Column(name = "BRIEF")
	private String brief;
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
