package com.epam.yalexeyenko.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public class Status extends BaseEntity {
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<News> newslist;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<News> getNewslist() {
		return newslist;
	}
	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}
	
	
}
