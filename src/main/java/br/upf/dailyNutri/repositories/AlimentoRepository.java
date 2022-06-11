package br.upf.dailyNutri.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.dailyNutri.entities.AlimentoEntity;

public interface AlimentoRepository extends JpaRepository<AlimentoEntity, Long> {
	
	List<AlimentoEntity> findByNome(String nome);
	
	public List<AlimentoEntity> findByPartNome(String partNome);
	
}
