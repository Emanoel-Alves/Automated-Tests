package api.test.locadora.controller;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import api.test.locadora.controller.FilmeService;
import api.test.locadora.repository.FilmeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class FilmeServiceTest {
	
	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private FilmeService filmeService;
	
	@AfterEach
    public void setUp() throws Exception {
		
//			repository.deleteAll();
		
    }
	
	@DisplayName("Teste passando String null")
	@Test
    public void testPassValueNull() throws Exception{
		
		assertEquals(filmeService.saveFilme("null", "manel", new BigDecimal("5.0"), new BigDecimal("5.0"), "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(filmeService.saveFilme("Dogras", "null", new BigDecimal("5.0"), new BigDecimal("5.0"), "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(filmeService.saveFilme("Jorge", "manel", new BigDecimal("5.0"), new BigDecimal("5.0"), "null").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());

    }
	
	@DisplayName("Teste passando null")
	@Test
	public void testPassNull() throws Exception{
		
		assertEquals(filmeService.saveFilme(null, "manel", new BigDecimal("5.0"), new BigDecimal("5.0"), "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(filmeService.saveFilme("Dogras", null, new BigDecimal("5.0"), new BigDecimal("5.0"), "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(filmeService.saveFilme("Jorge", "manel", new BigDecimal("5.0"), new BigDecimal("5.0"), null).getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		
		assertEquals(filmeService.saveFilme("Carlos", "manel", null, new BigDecimal("5.0"), "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(filmeService.saveFilme("Lucas", "manel", new BigDecimal("5.0"), null, "manel").getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		
	}
	
	@DisplayName("Teste passando nome aceitavel")
	@Test
	public void testPassNameOk() throws Exception{
		
		assertEquals(filmeService.saveFilme("A Freira", "Terror", new BigDecimal("5.0"), new BigDecimal("5.0"), "Causa medo").getStatusCodeValue(), HttpStatus.OK.value());
		assertEquals(filmeService.saveFilme("It", "Terror", new BigDecimal("5.0"), new BigDecimal("5.0"), "Muito medo").getStatusCodeValue(), HttpStatus.OK.value());
		
	}
	
	@DisplayName("Teste de busca")
	@Test
	public void testForSearch() throws Exception{
		assertEquals(HttpStatus.OK.value(), filmeService.search("A Freira").getStatusCodeValue());
	}
	
	@DisplayName("Teste de retorno dos filmes cadastrados")
	@Test
	public void testForReturnList() throws Exception{
		assertEquals(HttpStatus.OK.value(), filmeService.getFilmes().getStatusCodeValue());
	}
	
	@DisplayName("Tesde de unicidade dos filmes cadastrados")
	@Test
	public void testOfUnicidade() throws Exception {
		if(HttpStatus.OK.value() == filmeService.saveFilme("It", "Terror", new BigDecimal("5.0"), new BigDecimal("5.0"), "Muito medo").getStatusCodeValue()) {
			fail("O metodo salvar cadastra mais de um filme com o mesmo nome ou parametros com dados iguais");
		}
	}
	
	@DisplayName("Teste de atualizacao de filme")
	@Test
	public void testForUpdate() throws Exception{
		assertEquals(HttpStatus.OK.value(), filmeService.updateFilme(331, "null", "Medo", new BigDecimal("12.0"), new BigDecimal("22.0"), "Filme so para maiores").getStatusCodeValue());
	}
}
