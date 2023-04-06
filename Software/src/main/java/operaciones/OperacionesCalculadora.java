package operaciones;

import java.util.Arrays;
import java.math.*;

public class OperacionesCalculadora {
	
	private double resultado = 0.0;
	private double memoria = 0.0;
	
	public double sumar(double operando1, double operando2) {
		
		resultado = operando1 + operando2;		
		return resultado;
		
	}
	
	public double restar(double operando1, double operando2) {
		
		resultado = operando1 - operando2;
		return resultado;
	}
	
	public double multiplicar(double operando1, double operando2) {
		
		resultado = operando1 * operando2;
		return resultado;
	}
	
	public double dividir(double dividendo, double divisor) throws Exception{
		
		if (divisor == 0) {
			if (dividendo == 0) throw new Exception ("Indeterminacion");
			else throw new Exception ("Infinito");
		}
		
		resultado =  dividendo / divisor;
		return resultado;
	}
	
	public String[] getOperaciones(int numeroBotonesDisponibles) {
		String opciones [] = new String [4];
		opciones [0] = "X^2";
		opciones [1] = "sqrt(x)";
		opciones [2] = "ln(x)";
		opciones [3] = "tg(x)";
		
		String opcionesElegidas [] = new String [numeroBotonesDisponibles];
		opcionesElegidas = Arrays.copyOf(opciones, numeroBotonesDisponibles); //Copia tantas posiciones de "opciones" como veces le diga el valor "numeroBotonesDisponibles".
		
		return opcionesElegidas;
	}
	
	public double operar (int numeroDeOperacion, double operando) throws Exception{
		
		double resultado =0.0;
		
		switch (numeroDeOperacion) {
		
		case 0: //Elevar al cuadrado
			resultado = operando*operando;
			break;
			
		case 1: //Raíz cuadrada
			
			if (operando <0) {
				throw new Exception ("No existe la ráiz de un número negativo");
			} else {
			resultado = Math.sqrt(operando);
			}			
			break;
	
		case 2: //Logaritmo neperiano
			
			if (operando <= 0) {
				throw new Exception ("No se puede realizar un ln de un numero menor o igual que 0");
			} else {
				resultado = Math.log(operando);
			}
			break;
		
		case 3: //Tangente
			
			if (operando % (Math.PI) != 0 && operando % (Math.PI/2) == 0) { //Esto no va a funcionar porque Math.tan no considera la opción de que tan(pi/2) no exista
				
				throw new Exception ("La tangente de pi/2 + n*pi no existe");
				
			} else {
				resultado = Math.tan(operando);
			}
			break;	
		
		default:
			
			throw new Exception ("La opción elegida no está implementada");
			
		}
		return resultado;
	}
	
	public void memoriaLimpiar() {
		memoria = 0.0;
	}
	
	public double obtenerUltimoResultado() {
		return resultado;
	}
	
	public void memoriaAniadir() {
		memoria = memoria + resultado;
	}
	
	public double memoriaObtener () {
		return memoria;
	}
	
}
