package entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultadoOperaciones {
	
	private double resultado;

	@XmlElement
	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
	
	public ResultadoOperaciones() {
		
	}
	
	public ResultadoOperaciones(double resultado) {
		this.resultado = resultado;
	}

}
