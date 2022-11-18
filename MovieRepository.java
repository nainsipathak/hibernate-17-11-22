package repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.hibernate.jpa.demo.Movie;

public class MovieRepository {
	private final EntityManager entityManager;

	  public MovieRepository(final EntityManager entityManager) {
	    this.entityManager = entityManager;
	  }

	  public List<Movie> findAll() {
	    return entityManager.createQuery("FROM movies ", Movie.class).getResultList();
	  }

	  public List<Movie> findAllByName(String title) {
	    return entityManager.createQuery("SELECT m FROM movies m WHERE m.title = :title", Movie.class)
	        .setParameter("title", title)
	        .getResultList();
	  }
	
	  //12.
	  public Movie findMovieByName(String title) {
		    return entityManager.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class)
		        .setParameter("title", title)
		        .getSingleResult();
		  }
	  //13.
	  public Optional<Movie> findById(final int id) {
		    return Optional.ofNullable(entityManager.find(Movie.class, id));
		  }

	  public Movie save(final Movie movie) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.persist(movie);
	      transaction.commit();
	      return movie;
	    } catch (final Exception e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      return null;
	    }
	  }
//15.
	  public void remove(final Movie movie) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.remove(movie);
	      transaction.commit();
	    } catch (final Exception e)
	       {
	      if (transaction != null) 
	       {
	        transaction.rollback();
	      }
	    }
	  }
	  //16.
	  	public void removeall() {
	  		List<Movie> movies = findAll();
	  
	  		for(Movie movie: movies) {
	  			EntityTransaction transaction = null;
	  			try {
	  				transaction = entityManager.getTransaction();
	  				if(!transaction.isActive()) {
	  					transaction.begin();
			  }
			  
		  	entityManager.remove(movie);
		  	transaction.commit();
		  } catch(final Exception e) {
			  if(transaction != null) {
				  transaction.rollback();
		  }
	  }
    }
  }
}
