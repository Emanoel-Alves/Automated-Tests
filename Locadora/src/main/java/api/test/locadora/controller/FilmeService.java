package api.test.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import api.test.locadora.model.Filme;
import api.test.locadora.repository.FilmeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/Locadora/api")
@Service
public class FilmeService {
	
	@Autowired
	FilmeRepository filmeRepository;

	@GetMapping("/filmes")
	public ResponseEntity<List<Filme>> getFilmes() {
		return new ResponseEntity<List<Filme>>(filmeRepository.findAll(), HttpStatus.OK);
	}
//	@GetMapping("/search/{name}") 
//	public ResponseEntity <List<Filme>> search(@PathVariable(value = "name") String name){
//		System.out.println("name: "+name);
//		List<Filme> filme = filmeRepository.findByFilmeNameStartingWith(name);
//	
//		return new ResponseEntity<List<Filme>>(filme,HttpStatus.OK);
//	}
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
	public ResponseEntity<Filme> saveFilme(String name, String category, double price, double quantity,
			String description) {

		if (name == null || category == null || description == null
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
			double price, double quantity, String description) {

		if (name == null || category == null || description == null
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
	
	@PostConstruct
    public void population() {
		filmeService.saveFilme("Os 3 patetas", "Acao",  5, 4, "Filme de comedia");
		filmeService.saveFilme("Pequena Sereia", "Romance",  5, 4, "Filme de peixe que fala");
		filmeService.saveFilme("Matrix", "Acao",  5, 4, "Nao precisava de um 4 filme");
		filmeService.saveFilme("Fuga das galinha", "Acao",  5, 4, "Filme de comedia");
	}
}
