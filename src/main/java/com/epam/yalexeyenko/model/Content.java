package com.epam.yalexeyenko.model;

public class Content extends BaseEntity {
	private String content;
	private News news;

	public Content() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	
}
