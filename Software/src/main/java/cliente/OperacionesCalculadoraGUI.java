package cliente;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import CalculadoraGUI.ICalculadora;
import entidades.ResultadoOperaciones;
import entidades.SelectorOperaciones;

public class OperacionesCalculadoraGUI implements ICalculadora {

	private WebTarget target;
	private Cookie sessionId;
	
	public OperacionesCalculadoraGUI() {
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/p4/rest/calculadora");
		sessionId = null;
		
	}
	
	@Override
	public double sumar(double operando1, double operando2) {
		Builder peticion = target.path("/sumar")
								.queryParam("operando1", operando1)
								.queryParam("operando2", operando2)
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		javax.ws.rs.core.Response response = peticion.get();
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
		return resultado.getResultado();
	}

	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
		Builder peticion = target.path("/dividir")
								.queryParam("dividendo", dividendo)
								.queryParam("divisor", divisor)
								.request()
								.accept(MediaType.APPLICATION_XML)
								.accept(MediaType.TEXT_PLAIN);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		javax.ws.rs.core.Response response = peticion.get();
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		
		if (response.getStatusInfo()==Status.PRECONDITION_FAILED) {
			
			throw new Exception(response.readEntity(String.class));
		}
		
		ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
		return resultado.getResultado();
								
								
	}

	@Override
	public String[] getOperaciones(int numeroBotonesDisponibles) {
		Builder peticion = target.path("/operaciones")
								.queryParam("numeroOperaciones", numeroBotonesDisponibles)
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		
		javax.ws.rs.core.Response response = peticion.get();
		
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		
		SelectorOperaciones resultado = response.readEntity(SelectorOperaciones.class);
		return resultado.getNumeroOperaciones();
		}

	@Override
	public void memoriaAniadir() {
		Builder peticion = target.path("/memoria")
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		peticion.head();		

	}

	@Override
	public void memoriaLimpiar() {
		Builder peticion = target.path("/memoria")
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		peticion.delete();

		}

	@Override
	public double memoriaObtener() {
		Builder peticion = target.path("/memoria")
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		
		javax.ws.rs.core.Response response = peticion.get();
		
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		
		ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
		return resultado.getResultado();

	}

	@Override
	public double multiplicar(double operando1, double operando2) {
		Builder peticion = target.path("/multiplicar")
								.queryParam("operando1", operando1)
								.queryParam("operando2", operando2)
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		javax.ws.rs.core.Response response = peticion.get();
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		
		ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
		return resultado.getResultado();
	}

	@Override
	public double obtenerUltimoResultado() {
		Builder peticion = target.path("/ur")
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);

		javax.ws.rs.core.Response response = peticion.get();

		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

	ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
	return resultado.getResultado();
	}

	@Override
	public double operar(int numeroDeOperacion, double operando) throws Exception {
		Builder peticion = target.path("/operar")
								.queryParam("operacion", numeroDeOperacion)
								.queryParam("operando", operando)
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);

		javax.ws.rs.core.Response response = peticion.get();

		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
		
		if (response.getStatusInfo()==Status.PRECONDITION_FAILED) {
			
			throw new Exception(response.readEntity(String.class));
		}

		ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
		return resultado.getResultado();
	}

	@Override
	public double restar(double operando1, double operando2) {
		Builder peticion = target.path("/restar")
								.queryParam("operando1", operando1)
								.queryParam("operando2", operando2)
								.request()
								.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		
		javax.ws.rs.core.Response response = peticion.get();
		
		if(sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

	ResultadoOperaciones resultado = response.readEntity(ResultadoOperaciones.class);
	return resultado.getResultado();
	}
	
}