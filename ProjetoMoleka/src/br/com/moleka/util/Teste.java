package br.com.moleka.util;

public class Teste {
	
	public static int[] inverter(int valores[],int quantidade){
		
		int valoresInvertidos [] = new int[quantidade];
		
		for(int i=0; i < valores.length; i++){
			
				valoresInvertidos[i] = valores[quantidade - 1];
				
			quantidade +=-1;
		}
		
		return valoresInvertidos;
	}
	
	public static int[] inverter(int valores[]){
		
		int tamanhoVetor = valores.length;
		int valoresInvertidos [] = new int[tamanhoVetor];
			
		for(int i=0; i < valores.length; i++){	
				valoresInvertidos[i] = valores[tamanhoVetor - 1];			
				tamanhoVetor +=-1;
		}
		
		return valoresInvertidos;
	}

	public static void main(String[] args) {
		
		int valores [] = {1,2,3,4,5,6};
		
		int valoresInvertidos[] = Teste.inverter(valores);
		
		for(int i=0; i < valoresInvertidos.length; i++){
			
			System.out.println(valoresInvertidos[i]);
		}

	}

}
