package servidor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import entidades.ResultadoOperaciones;
import entidades.SelectorOperaciones;
import operaciones.OperacionesCalculadora;

@Path("/calculadora")
public class OperacionesCalculadoraREST {
	
	@Context private HttpServletRequest hsr;
	private OperacionesCalculadora getOperacionesCalculadora () {
		final String ATRIBUTOCALCULADORA = "operaciones";
		final HttpSession sesion = hsr.getSession();
		OperacionesCalculadora operaciones =(OperacionesCalculadora)sesion.getAttribute(ATRIBUTOCALCULADORA);
		if(operaciones == null) {
			operaciones = new OperacionesCalculadora();
			sesion.setAttribute(ATRIBUTOCALCULADORA, operaciones);
		}
		return operaciones;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/sumar")
	public Response sumar (@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado = getOperacionesCalculadora().sumar(operando1, operando2);
		
		//respuesta = respuesta.entity(resultado);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		
		return respuesta.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/restar")
	public Response restar (@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado = getOperacionesCalculadora().restar(operando1, operando2);
		
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		
		return respuesta.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/multiplicar")
	public Response multiplicar (@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado = getOperacionesCalculadora().multiplicar(operando1, operando2);
		
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		
		return respuesta.build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("/dividir")
	public Response dividir (@QueryParam("dividendo") double dividendo, @QueryParam("divisor") double divisor){
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado;
		
		try {
			
			resultado = getOperacionesCalculadora().dividir(dividendo, divisor);
			respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
			
		} catch (Exception e) {

			respuesta = Response.status(Response.Status.PRECONDITION_FAILED);
			respuesta.entity(e.getMessage());
			respuesta.type(MediaType.TEXT_PLAIN);
		}		
		
		
		return respuesta.build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("/operar")
	public Response operar (@QueryParam("operacion") int operacion, @QueryParam("operando") double operando) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado;
		
		try {
			
			resultado = getOperacionesCalculadora().operar(operacion, operando);
			respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
			
		} catch (Exception e) {

			respuesta = Response.status(Response.Status.PRECONDITION_FAILED);
			respuesta.entity(e.getMessage());
			respuesta.type(MediaType.TEXT_PLAIN);
		}		
		
		
		return respuesta.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/operaciones")
	public Response operaciones (@QueryParam("numeroOperaciones") int numeroOperaciones) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		String [] resultado = getOperacionesCalculadora().getOperaciones(numeroOperaciones);
		
		respuesta = respuesta.entity(new SelectorOperaciones(resultado));
		
		return respuesta.build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/ur")
	public Response ur () {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado = getOperacionesCalculadora().obtenerUltimoResultado();
		
		//respuesta = respuesta.entity(resultado);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		
		return respuesta.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/memoria")
	public Response memoriaObtener () {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		double resultado = getOperacionesCalculadora().memoriaObtener();
		
		//respuesta = respuesta.entity(resultado);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		
		return respuesta.build();
	}
	
	@HEAD
	@Produces(MediaType.APPLICATION_XML)
	@Path("/memoria")
	public Response memoriaAniadir () {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		getOperacionesCalculadora().memoriaAniadir();
		
		return respuesta.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Path("/memoria")
	public Response memoriaBorrar () {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		
		getOperacionesCalculadora().memoriaLimpiar();
		
		return respuesta.build();
	}
	
	
	
}
