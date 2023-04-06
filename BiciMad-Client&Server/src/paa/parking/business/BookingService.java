package paa.parking.business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.persistence.*;

import paa.parking.model.Booking;
import paa.parking.model.Parking;
import paa.parking.persistence.DAOException;
import paa.parking.persistence.JpaParkingDAO;
import paa.parking.persistence.*;


public class BookingService implements IBookingService {
	
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT_NAME = "Practica5";
	/**
	   * Constructor de la clase.
	   * @param name nombre de la unidad de persistencia PERSISTENCE_UNIT_NAME
	   * @param path Es el path que generado en tiempo de ejecución
	   */
	public BookingService (String path) {    
	    @SuppressWarnings("rawtypes")
	    Map properties = new HashMap();
	    properties.put("javax.persistence.jdbc.url", "jdbc:ucanaccess://"+path+";newdatabaseversion=V2010");
	    emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
	}
	
	public BookingService () {    
		
	}

	public Parking createParking(String name, String address, int totalSpaces, int bookableSpaces, double longitude,	double latitude) {
		EntityManager manager = emf.createEntityManager();
		Parking p1=null;
		try {
			manager.getTransaction().begin();
			if(totalSpaces>=bookableSpaces && bookableSpaces>0 && totalSpaces>0 && latitude<=90 && latitude>=-90 && longitude<=180 && longitude>=-180) {
				
			p1=new Parking (null, name,  address,totalSpaces, bookableSpaces  ,  longitude, latitude);	
			manager.persist(p1);
			manager.getTransaction().commit();
			}else {
				throw new BookingServiceException ("Algun dato no es correcto");
			}
			 
		}catch(BookingServiceException e) {
			throw new BookingServiceException ("Algun dato no es correcto");
		}finally {
			manager.close();
		}
		return p1;
	}

	
	@Override
	public Parking findParking(Long parkingCode) {
		EntityManager manager = emf.createEntityManager();
		try {
			manager.getTransaction().begin();
			Parking p1= manager.find(Parking.class, parkingCode);
			return p1;
		}catch(BookingServiceException e) {
			if(manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			return null;
		}finally {
			manager.close();
		}
	}


	@Override
	public List<Parking> findParkings() {
		EntityManager manager = emf.createEntityManager();

		JpaParkingDAO dao = new JpaParkingDAO(manager);
		List<Parking> par= new ArrayList<Parking>();
		try {
			manager.getTransaction().begin();
			par=dao.findAll();
			manager.getTransaction().commit();
		}catch(BookingServiceException e) {
			if(manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}finally {
			manager.close();
			
		}
		return par;
	}

	
	@Override
	public int availableSpaces(Long parkingCode, LocalDate date) {
		int devuelto=0;
		EntityManager manager = emf.createEntityManager();
		List<Booking> B= new ArrayList<Booking>();
		try {
			manager.getTransaction().begin();
			
			devuelto=findParking(parkingCode).getBookableSpaces();
			B=findParking(parkingCode).getBookings();	
			for(Booking b: B) {
				if(b.getDate().equals(date)) {
					devuelto--;
				}
			}
			manager.getTransaction().commit();
		}catch(BookingServiceException e) {
			if(manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw new BookingServiceException ("Algun dato no es correcto");
		}finally {
			manager.close();
		}
		return devuelto;
	}

	
	@Override
	public Booking createBooking(String licencePlate, LocalDate date, Long parkingCode) {
		EntityManager manager = emf.createEntityManager();
		Booking b1=null;
		LocalDate hoy = LocalDate.now();
		LocalDate fechaLimite = hoy.plusDays(15);
		
		JpaParkingDAO parkingDAO= new JpaParkingDAO(manager);
		JpaBookingDAO bookingDAO = new JpaBookingDAO(manager);
		
		if(fechaLimite.isBefore(date)) {
			throw new BookingServiceException("No se puede reservar para esa fecha todavia");
		}
		
		for(Parking p : parkingDAO.findAll()) {
			for(Booking b : p.getBookings()) {
				if(b.getLicencePlate().equals(licencePlate))
					throw new BookingServiceException("No se puede reservar, ya existe una reserva con esa matricula");
			}
		}
		
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			b1=new Booking(licencePlate, date, findParking(parkingCode));
			b1 = bookingDAO.create(b1);
			manager.getTransaction().commit();
		}finally {
			manager.close();
		}
		return b1;
	}
		

	@Override
	public void cancelBooking(Long bookingCode) {
		
		EntityManager manager = emf.createEntityManager(); 
		JpaBookingDAO T = new JpaBookingDAO(manager);
		Booking b1;
		try {	
			manager.getTransaction().begin();
			b1=T.find(bookingCode);

			if(!LocalDate.now().isBefore(b1.getDate()) || LocalDate.now().isEqual(b1.getDate()) ) {
				JOptionPane.showMessageDialog(null,"No se puede cancelar una reserva el mismo dia");
			}else {
				manager.remove(b1);
				manager.getTransaction().commit();
			}
		}catch(BookingServiceException e) {
			manager.getTransaction().rollback();
			throw new BookingServiceException ("Fallo al eliminar");
		}finally {
			manager.close();
		}
	}
}