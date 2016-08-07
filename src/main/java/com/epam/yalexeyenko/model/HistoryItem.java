package com.epam.yalexeyenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORY")
public class HistoryItem extends BaseEntity {
	@Column(name = "NEWS_ID")
	private Long newsId;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
}
