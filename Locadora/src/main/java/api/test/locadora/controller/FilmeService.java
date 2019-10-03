package api.test.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import api.test.locadora.model.Filme;
import api.test.locadora.repository.FilmeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/Locadora/api")
public class FilmeService {
	
	@Autowired
	FilmeRepository filmeRepository;
	
	@GetMapping("/filmes")
	public ResponseEntity<List<Filme>> getFilmes() {
		return new ResponseEntity<List<Filme>>(filmeRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}") 
	public ResponseEntity<Filme> search(@PathVariable(value = "name") String name){

		Optional<Filme> filme = filmeRepository.findByName(name);
	
		if (filme.isPresent()) {
			return new ResponseEntity<Filme>(filme.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/filmes/{id}")
	public ResponseEntity<Filme> getProduct(@PathVariable(value = "id") Integer id) {

		Optional<Filme> filme = filmeRepository.findById(id);

		if (filme.isPresent()) {
			return new ResponseEntity<Filme>(filme.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/filme")
	public ResponseEntity<Filme> saveFilme(String name, String category, BigDecimal price, BigDecimal quantity,
			String description) {

		if (name == null || category == null || price == null || quantity == null || description == null
			 || name.equals("null") || category.equals("null") || description.equals("null")) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Filme filme = new Filme(name, category, price, quantity, description);
		filmeRepository.save(filme);

		return new ResponseEntity<Filme>(filme, HttpStatus.OK);

	}

	@DeleteMapping("/filme/{id}")
	public ResponseEntity<?> deleteFilme(@PathVariable(value = "id") Integer id) {

		if (filmeRepository.existsById(id)) {

			filmeRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/filme/{id}")
	public ResponseEntity<Filme> updateFilme(@PathVariable("id") Integer id, String name, String category,
			BigDecimal price, BigDecimal quantity, String description) {

		if (name == null || category == null || price == null || quantity == null || description == null
				|| category.equals("null") || description.equals("null")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Optional<Filme> filme = filmeRepository.findById(id);

		if (filme.isPresent()) {
			filme.get().setName(name);
			filme.get().setCategory(category);
			filme.get().setPrice(price);
			filme.get().setQuantity(quantity);
			filme.get().setDescription(description);
			filmeRepository.save(filme.get());

			return new ResponseEntity<Filme>(filme.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
