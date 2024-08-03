package edu.tus.wizworld.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String title;
	private String author;
	private String illustratedBy;
	private double realRetailPrice;
	private double onlinePrice;
	private String image;
	private String series;
	@Enumerated(EnumType.STRING)
	private Rating rating;
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIllustratedBy() {
		return illustratedBy;
	}
	public double getRealRetailPrice() {
		return realRetailPrice;
	}
	public double getOnlinePrice() {
		return onlinePrice;
	}
	public String getImage() {
		return image;
	}
	public String getSeries() {
		return series;
	}
	public Rating getRating() {
		return rating;
	}
	
	
}