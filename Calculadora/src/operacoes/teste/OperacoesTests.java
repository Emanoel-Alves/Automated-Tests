package operacoes.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import operacoes.Operacoes;
@DisplayName("Testando calculadora")
public class OperacoesTests {
	
	@Test
	@DisplayName("xxxx")
	public void somarTest() {
		Operacoes calculadora = new Operacoes();
		assertEquals("Soma é verdadeira", calculadora.soma(1, 1), 2);
	}
	
	@Test
	@DisplayName("1 - 1 = 0")
	public void subtrairTest() {
		Operacoes calculadora = new Operacoes();
		assertEquals("Subtração é verdadeira", calculadora.subtrair(1, 1), 2);
	}
	
	@DisplayName("1 + 1 = 2")
	@Test
	public void multiplicatTest() {
		Operacoes calculadora = new Operacoes();
		assertEquals("Soma é verdadeira", calculadora.multiplicar(1, 1), 2);
	}
	
	@DisplayName("1 + 1 = 2")
	@Test
	public void dividirTest() {
		Operacoes calculadora = new Operacoes();
		assertEquals("Soma é verdadeira", calculadora.dividir(1, 0), 0);
		fail("A divisao esta errada");
	}
}
