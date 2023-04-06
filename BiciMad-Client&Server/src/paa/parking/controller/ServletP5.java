package paa.parking.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import paa.parking.business.BookingService;
import paa.parking.business.IBookingService;
import paa.parking.model.Booking;
import paa.parking.model.Parking;



@WebServlet("/")
public class ServletP5 extends HttpServlet {
	
	private IBookingService bs;
	private Cookie c= new Cookie ("Prueba", String.valueOf(0));
	private int contCookie = 0;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletP5() {
        super();
    }
    
    
    /**
     * Inicializar un servlet
     */
  public void init() {
	  	  
      String absoluteDiskPath = getServletContext().getRealPath("./WEB-INF/bdatos/PAA_ParkingsNew.mdb");
         
      
      try {
    	  //System.out.println(absoluteDiskPath);
    	  bs = new BookingService(absoluteDiskPath);
       }
      catch (Exception e) {
      System.out.println ("Error al crear el dao.\n");
      e.printStackTrace();
      }
  }
  
  
  
  
  /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String accion = request.getParameter("accion");
		
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json; charset=UTF-8");
		response.setContentType("text/plain");		
			
		if(accion == null){
			accion = "findParkings";
			//accion = "";
		}
		
		
		System.out.println(request.getQueryString());
		
		switch(accion) {
			case "findParking":
				listarParking(request, response);			
			break;
					
			default:
				contCookie++;
				c.setValue(String.valueOf(contCookie));	
				response.addCookie (c);
				listarParkings(request, response);	
			break;
		}
		
		
	}
	
  
  
  
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	
	private void listarParkings(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Parking> datos = bs.findParkings();
		
		request.setAttribute("datos", datos);
		
		RequestDispatcher d = request.getRequestDispatcher("/parkings.jsp");
		
		d.forward(request, response);
		
	}
	
	
	private void listarParking(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Long code = Long.parseLong(request.getParameter("parkingCode").toString());
		
		List<Booking> datos = bs.findParking(code).getBookings();
		
		request.setAttribute("datos", datos);
		
		RequestDispatcher d = request.getRequestDispatcher("/bookings.jsp");
		
		d.forward(request, response);
	
	}
	
	
	
	
}	
