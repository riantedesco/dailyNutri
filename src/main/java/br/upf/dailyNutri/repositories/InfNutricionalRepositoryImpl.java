package br.upf.dailyNutri.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.dailyNutri.entities.InfNutricionalEntity;

@Repository
public abstract class InfNutricionalRepositoryImpl implements InfNutricionalRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<InfNutricionalEntity> findByAlimentoId(Long alimentoId) {
		List<InfNutricionalEntity> entityList = new ArrayList<>();
		
		TypedQuery<InfNutricionalEntity> query = em.createNamedQuery("InfNutricionalEntity.findByAlimentoId", InfNutricionalEntity.class);
		query.setParameter("alimentoId", alimentoId);
		entityList = query.getResultList();
		
		return entityList;
	}
}
