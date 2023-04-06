<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import = "javax.servlet.http.HttpServlet"
	import = "paa.parking.model.*"
	import = "java.util.List"
 %>
 
 <%!
 
	private String bookingsToHTML(List<Booking> bookings) {
		String sBookings = "";
		
		for(Booking b : bookings){
			sBookings += "<hr>";
			sBookings += b.toString();
		}
		
		return sBookings;	
	}
 
 %>
    
    
<!DOCTYPE html>
<html>
<head>
<title>Bookings</title>
</head>
<body>

<h2>Lista de bookings</h2> <!--  Se puede incluir algún dato relativo al parking -->

	<%
	 	List<Booking> bookings = (List<Booking>) request.getAttribute("datos");
		
	 	out.println(bookingsToHTML(bookings));
	%>

</body>
</html>