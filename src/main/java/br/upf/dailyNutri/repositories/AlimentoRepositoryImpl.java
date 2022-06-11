package br.upf.dailyNutri.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.dailyNutri.entities.AlimentoEntity;

@Repository
public abstract class AlimentoRepositoryImpl implements AlimentoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AlimentoEntity> findByNome(String nome) {
		List<AlimentoEntity> entityList = new ArrayList<>();
		
		TypedQuery<AlimentoEntity> query = em.createNamedQuery("AlimentoEntity.findByNome", AlimentoEntity.class);
		query.setParameter("nome", nome);
		entityList = query.getResultList();
		
		return entityList;
	}
	
	public List<AlimentoEntity> findByPartNome(String partNome) {
        List<AlimentoEntity> entityList = new ArrayList<>();
        try {
        	TypedQuery<AlimentoEntity> query = em.createNamedQuery("AlimentoEntity.findByPartNome", AlimentoEntity.class);
            query.setParameter(1, partNome);
            entityList = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return entityList;
    }
}
