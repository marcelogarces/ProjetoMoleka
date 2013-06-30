package br.com.moleka.testeUnitario;

import br.com.moleka.servico.Calculo;
import junit.framework.TestCase;

public class CalculoTest extends TestCase{
	
	public void testExecutarCalculoSoma(){
		
		Double valor1 = 5.0;
		Double valor2 = 2.0;
		
		Double valorEsperado = 7.0;
		
		Calculo calculo = new Calculo(); 
		
		Double valorObtido = calculo.somar(valor1, valor2);
		
		assertEquals(valorEsperado, valorObtido);
		
	}

}
