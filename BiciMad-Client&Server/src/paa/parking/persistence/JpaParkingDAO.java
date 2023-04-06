package paa.parking.persistence;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import paa.parking.model.*;

public class JpaParkingDAO extends JpaDAO<Parking, Long> {

	
	
	public JpaParkingDAO(EntityManager em) {
		super(em, Parking.class);
		
	}
}
