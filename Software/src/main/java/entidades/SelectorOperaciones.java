package entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SelectorOperaciones {
	
	private String [] numeroOperaciones;	
	
	@XmlElement
	public String[] getNumeroOperaciones() {
		return numeroOperaciones;
	}

	public void setNumeroOperaciones(String[] numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}

	public SelectorOperaciones() {
		
	}
	
	public SelectorOperaciones(String[] numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}

}
