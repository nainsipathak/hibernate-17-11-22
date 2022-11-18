package com.hibernate.jpa.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "genres")

public class Genre {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id")
	 private int id;
	 
	 @Column(name="name")
	 private String name;
	  
	 @OneToMany
	 @JoinColumn(name = "genre_id")
	 private List<Movie> movies;

	 
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Genre(int id, String name, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.movies = movies;
	}

	public Genre(String name, List<Movie> movies) {
		super();
		
		this.name = name;
		this.movies = movies;
	}
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
