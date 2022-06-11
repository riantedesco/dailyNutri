package br.upf.dailyNutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.dailyNutri.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);

}
