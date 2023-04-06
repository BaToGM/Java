package paa.parking.persistence;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import paa.parking.model.Booking;

public class JpaBookingDAO extends JpaDAO<Booking, Long> {

	public JpaBookingDAO(EntityManager em) {
		super(em, Booking.class);
	}
	
	public List<Booking> findAllBookingsSortedByDate() {
		Query q = em.createQuery("select * from Booking ORDER BY date ASC");
		List<Booking> lista = q.getResultList();
		return lista;
		
	}
}
