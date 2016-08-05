package com.epam.yalexeyenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.epam.yalexeyenko.dto.Dto;

@Entity
@Table(name = "NEWS_BOARD")
public class NewsHistoryItem implements Dto {
	@Column(name = "NEWS_ID")
	private Long newsId;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}


}
