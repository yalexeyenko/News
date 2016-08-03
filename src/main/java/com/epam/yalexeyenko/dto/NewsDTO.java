package com.epam.yalexeyenko.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class NewsDTO implements Dto {
	private Long id;
	
	@NotBlank(message = "NotBlank.newsDTO.title")
	@Size(min = 3, max = 100, message = "Size.newsDTO.title")
	private String title;
	
	@NotBlank(message = "NotBlank.newsDTO.brief")
	@Size(min = 3, max = 1000, message = "Size.newsDTO.brief")
	private String brief;
	
	@NotBlank(message = "NotBlank.newsDTO.date")
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/(0[1-9]|1\\d|2\\d|3[01])\\/(19|20)\\d{2}$", message = "Pattern.newsDTO.date")
	private String date;
	
	@NotBlank(message = "NotBlank.newsDTO.content")
	@Size(min = 3, max = 2000, message = "Size.newsDTO.content")
	private String content;
	
	private String status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
