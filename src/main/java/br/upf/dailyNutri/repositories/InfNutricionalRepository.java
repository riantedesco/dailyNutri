package br.upf.dailyNutri.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.dailyNutri.entities.InfNutricionalEntity;

public interface InfNutricionalRepository extends JpaRepository<InfNutricionalEntity, Long> {
	
	List<InfNutricionalEntity> findByAlimentoId(Long alimentoId);
	
}
