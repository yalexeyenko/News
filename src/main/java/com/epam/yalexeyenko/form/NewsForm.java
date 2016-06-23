package com.epam.yalexeyenko.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.epam.yalexeyenko.model.News;

public class NewsForm extends ActionForm {
	private News news;
	private String id;
	private String newsTitle;
	private String date;
	private String brief;
	private String content;
	private List<News> newsList;
	private String[] itemsToDelete;

	public String[] getItemsToDelete() {
		return itemsToDelete;
	}

	public void setItemsToDelete(String[] itemsToDelete) {
		this.itemsToDelete = itemsToDelete;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String title) {
		this.newsTitle = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

}
