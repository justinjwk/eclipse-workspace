package edu.jhu.web.proj.business;

import java.util.Date;

public class Book {

	private String title;
	private String description;
	private String author;
	private String genre;
	private String publishedDate;
	private double rating;
	private String review;
	private Boolean status;
	
	public Book() {
		
		this.title = "";
		this.description = "";
		this.author = "";
		this.genre = "";
		this.publishedDate = "";
		this.rating = 0.0;
		this.review = "";
		this.status = true;
	}
	
	public Book(String title, String description, String author, 
			String genre, String publishedDate, double rating,
			String review, Boolean status) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.genre = genre;
		this.publishedDate = publishedDate;
		this.rating = rating;
		this.review = review;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
