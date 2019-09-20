package api.test.locadora.repository;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class RepositoryTeste {
	
	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private FilmeService filmeService;
	
	@BeforeEach
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
	
	@DisplayName("2Teste passando nome aceitavel")
	@Test
	public void testPassNameOk() throws Exception{
		
		assertEquals(filmeService.saveFilme("A Freira", "Terror", new BigDecimal("5.0"), new BigDecimal("5.0"), "Causa medo").getStatusCodeValue(), HttpStatus.OK.value());
		assertEquals(filmeService.saveFilme("It", "Terror", new BigDecimal("5.0"), new BigDecimal("5.0"), "Muito medo").getStatusCodeValue(), HttpStatus.OK.value());
		
	}
	
}
