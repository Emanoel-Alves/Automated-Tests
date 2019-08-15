package api.test.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.test.locadora.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
	List <Filme> findByFilmeNameStartingWith(String name);
}
