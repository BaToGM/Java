package paa.parking.model;

import java.time.LocalDate;

import javax.persistence.*;

import java.io.*;


@Entity
public class Booking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long code;
	private String licencePlate;
	
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="CodeParking")
	private Parking parking;
	
	
	 
	public Booking( String licencePlate, LocalDate date, Parking parking) {
		this.licencePlate = licencePlate;
		this.date = date;
		this.parking = parking;
	}
	
	 
		public Booking(Long code, String licencePlate, LocalDate date, Parking parking) {
			this.code=code;
			this.licencePlate = licencePlate;
			this.date = date;
			this.parking = parking;
		}
		
	public Booking( ) {
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Parking getParking() {
		return parking;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((licencePlate == null) ? 0 : licencePlate.hashCode());
		result = prime * result + ((parking == null) ? 0 : parking.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (licencePlate == null) {
			if (other.licencePlate != null)
				return false;
		} else if (!licencePlate.equals(other.licencePlate))
			return false;
		if (parking == null) {
			if (other.parking != null)
				return false;
		} else if (!parking.equals(other.parking))
			return false;
		return true;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	@Override
	public String toString() {
		return "BookingCode: "+code+" Fecha: "+date + " LicencePlate:  "+ licencePlate+ "ParkingCode: "+parking.getCode();
	}

}
