package api.test.locadora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.test.locadora.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
	
	Optional<Filme> findByName(String name);
}
