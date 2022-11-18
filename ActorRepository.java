package repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.hibernate.jpa.demo.Actor;

public class ActorRepository {
	private final EntityManager entityManager;

	  public ActorRepository(final EntityManager entityManager) {
	    this.entityManager = entityManager;
	  }
//7.
	  public Actor save(final Actor actor) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.persist(actor);
	      transaction.commit();
	      return actor;
	    } catch (final Exception e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      return null;
	    }
	  }
//8.
	  public Optional<Actor> findById(final int id) {
	    return Optional.ofNullable(entityManager.find(Actor.class, id));
	  }
//9.
	  public List<Actor> findAllBornAfter(final int lowerBoundExclusive) {
	    return entityManager.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year", Actor.class)
	        .setParameter("year", lowerBoundExclusive)
	        .getResultList();
	  }
// 10.
	  public List<Actor> findAllWithLastNameEndsWith(final String surnameEndsWith) {
	    return entityManager.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName", Actor.class)
	        .setParameter("lastName", "%" + surnameEndsWith)
	        .getResultList();
	  }
	
		
	}

