package br.upf.dailyNutri.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.dailyNutri.entities.UserEntity;

@Repository
public abstract class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserEntity findByEmail(String email) {
		UserEntity entity = new UserEntity();
		try {
			TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByEmail", UserEntity.class);
			query.setParameter("email", email);
			query.setMaxResults(1);
			entity = query.getSingleResult();
		} catch (Exception e) {
            System.err.println("Error: " + e);
        }
		return entity;
	}
}
