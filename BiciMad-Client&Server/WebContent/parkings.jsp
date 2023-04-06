<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import = "javax.servlet.http.HttpServlet"
	import = "paa.parking.model.*"
	import = "java.util.List"
 %>

    
    
<!DOCTYPE html>
<html>
<head>
<title>Parkings</title>
</head>
<body>

<h2>Lista de parkings</h2>

	<%	
		List<Parking> parkings = (List<Parking>) request.getAttribute("datos");
	
	
	 for(Parking p: parkings){ %>
	
		<p> 
			<%String parkingCode = p.getCode().toString(); %>
			Parking code: <a href="ServletP5.java?accion=findParking&parkingCode=<%=p.getCode().toString()%>"><%=parkingCode%></a>
			<% out.print(p.toString()); %>
		</p>
		<hr>
	
	<%} %>
	
		<p>Ha visitado 
		
			
			<% Cookie ck[]=request.getCookies();
			
				out.print(" " + ck[0].getValue() + " "); 
			%>
		 	veces esta pagina.
		</p>
	
	 	

</body>
</html>