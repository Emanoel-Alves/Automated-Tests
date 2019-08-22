package api.test.locadora.controller;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.BeforeAll;

public class ControllerTeste {

	FilmeService filmeService;
	
	@BeforeAll
	void inicializr() {
		filmeService = new FilmeService();
	}

	
      

}
