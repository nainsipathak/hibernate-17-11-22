package com.hibernate.jpa.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
	private int id;
	
	@Column(name="name", nullable=false, length = 225)
	private String name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name = "year_of_birth")
	private int year_of_birth;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="actors_to_movies", 
			joinColumns = { @JoinColumn(name="actor_id")},
			inverseJoinColumns = { @JoinColumn(name = "movie_id")}
	)
	private List<Movie> movies;
		
	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Actor(int id, String name, String last_Name, int year_of_birth, List<Movie> movies, String last_name) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.year_of_birth = year_of_birth;
		this.movies = movies;
	}
	
    public Actor(String name, String last_name, int year_of_birth, List<Movie> movies) {
        this.name = name;
        this.last_name = last_name;
        this.year_of_birth = year_of_birth;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}
	 public List<Movie> getMovies() {
	        return movies;
	    }

	   public void setMovies(List<Movie> movies) {
		   this.movies=movies;
	   }

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", last_name=" + last_name + ", year_of_birth=" + year_of_birth
				+ ", movies=" + movies + "]";
	}
		
		
}
	


