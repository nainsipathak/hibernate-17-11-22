package com.hibernate.jpa.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MOVIE")
public class Movie {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @GenericGenerator(name="increment", strategy = "increment")
	  private int id;
	  private String title;
	  private int genre_id;
	  private int year_of_release;
	  @OneToMany
	  @JoinColumn(name = "genre_id")
	  private List<Movie> movies;
	  
	public Movie(String string, int i, Object of, Object object) {
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGenre() {
		return genre_id;
	}
	public void setGenre(int genre) {
		this.genre_id = genre;
	}
	public int getYear() {
		return year_of_release;
	}
	public void setYear(int year) {
		this.year_of_release = year;
	}
	public List<Movie> getActors() {
		// TODO Auto-generated method stub
		return null;
	}

}
