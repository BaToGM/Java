package cliente;

import CalculadoraGUI.CalculadoraGUI;
import CalculadoraGUI.ICalculadora;

public class ClienteCalculadora {

	public static void main(String[] args) {
		
		ICalculadora operaciones = new OperacionesCalculadoraGUI();
		CalculadoraGUI calculadora = new CalculadoraGUI(operaciones);

	}

}
