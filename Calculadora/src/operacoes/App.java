package operacoes;

public class App {

	public static void main(String[] args) {
		
		Operacoes calc = new Operacoes();
		
		System.out.println("Resultado da soma: " + calc.soma(5, 2));
		System.out.println("Resultado da subtração: " + calc.subtrair(5, 2));
		System.out.println("Resultado da multiplicação: " + calc.multiplicar(5, 2));
		System.out.println("Resultado da divisão: " + calc.dividir(5, 2));
	}
}
