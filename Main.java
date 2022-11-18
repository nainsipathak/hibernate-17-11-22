package com.hibernate.jpa.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;


import repository.ActorRepository;
import repository.GenreRepository;
import repository.MovieRepository;

public class Main {
	private static EntityManagerFactory factory = null;
	private static EntityManager entityManager = null;

	public static void main(String[] args) {
		
		try {
			factory = Persistence.createEntityManagerFactory("HibernateDemo");
			entityManager = factory.createEntityManager();
			
			Actor a1 = new Actor("Allu","Arjun",1982,null);
			Actor a2 = new Actor("Ram","Charan",1985,null);
			
			Actor a3 = new Actor("Mahesh","Ghattamaneni",1975,null);
			Actor a4 = new Actor("Venkatesh","Daggubati", 1960,null);
			
			// 7.	Saving objects of type Actor to the database
			ActorRepository actorRepositoty = new ActorRepository(entityManager);
			
			actorRepositoty.save(a1);
			actorRepositoty.save(a2);
			actorRepositoty.save(a3);
			actorRepositoty.save(a4);
			
			Genre g1 = new Genre("Action",null);
			
			Genre g2 = new Genre("Drama",null);
			
			GenreRepository genreRepository = new GenreRepository(entityManager);
			
			genreRepository.save(g1);
			
			genreRepository.save(g2);
			
			List<Actor> actors1 = new ArrayList<>();
			actors1.add(a1);
			actors1.add(a2);
			
			List<Actor> actors2 = new ArrayList<>();
			actors2.add(a3);
			actors2.add(a4);
			
			Movie m1 = new Movie("Yevadu", 2014, actors1, g1);
			
			Movie m2 = new Movie("SVSC", 2013, actors2, g2);
			
		
			// 8. look for objects in the database of type Actor by id
			
			Optional<Actor> a = actorRepositoty.findById(1);
			
			System.out.println("Actor: " + a);
			
			// 9. search for objects in the Actor type database that 
						// were born after a certain year (i.e. the year is a method parameter)
						
			List<Actor> actors = actorRepositoty.findAllBornAfter(1975);
						
			actors.forEach((ac) -> System.out.println(ac));
			
			// 10.	look for objects in the database of the Actor type, the names 
			// of which end with the specified value of the String type object
			
			//select * from actors where last_name like '%sh';
			
			actors = actorRepositoty.findAllWithLastNameEndsWith("sh");
			actors.forEach(ac -> System.out.println(ac));
		
			// 11.	adding Movie records to the database
				
			MovieRepository movieRepository = new MovieRepository(entityManager);
						
			movieRepository.save(m1);
			movieRepository.save(m2);
						
			
			//12.	searching Movie record by title
			
			Movie movie = movieRepository.findMovieByName("SVSC");
			System.out.println(movie);
			
			//13.	searching for Movie record by id
			Optional<Movie>	m = movieRepository.findById(2);
			System.out.println(m);
			
			//14.	returning all Movie records
			List<Movie> movies = movieRepository.findAll();
			movies.forEach(mv -> System.out.println(mv));
			
			//15.	removing one Movie record from the database.
			System.out.println("Removing One Movie :");
			System.out.println();
			
			//16.	removing all Movie records from the database
			System.out.println("Removing All Movie :");
			System.out.println();
		}
		catch(HibernateException e) {
			
			e.printStackTrace();
	}finally {
		
		
		if(entityManager != null)
			entityManager.close();
		
		if(factory != null)
			factory.close();
	}
	}
}