package paa.parking.model;

import java.util.*;

import javax.persistence.*;

import java.io.*;

@Entity
public class Parking implements Serializable {
	@Id
	@GeneratedValue
	private Long code;
	private String name;
	private String address;
	private int bookableSpaces;
	private int totalSpaces;
	private double longitude;
	private double latitude;
	@OneToMany(mappedBy="parking")
	private List<Booking> bookings;

	public Parking(Long code, String name, String address, int totalSpaces, int bookableSpaces, double longitude, double latitude
			) {
		super();
		this.code=code;
		this.name = name;
		this.address = address;
		this.bookableSpaces = bookableSpaces;
		this.totalSpaces = totalSpaces;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	public Long getCode() {
		return code;
	}
	
	public int getBookableSpaces() {
		return bookableSpaces;
	}

	public void setBookableSpaces(int plazasReservables) {
		this.bookableSpaces = plazasReservables;
	}

	public void setCode(Long id) {
		this.code = id;
	}

	public int getSpaces() {
		return totalSpaces;
	}

	public void setSpaces(int spaces) {
		this.totalSpaces = spaces;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public int getTotalSpaces() {
		return totalSpaces;
	}

	public void setTotalSpaces(int totalSpaces) {
		this.totalSpaces = totalSpaces;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Parking other = (Parking) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Name: " + name + ", Address: " + address + ", BookableSpaces: "
				+ bookableSpaces + ", TotalSpaces: " + totalSpaces + ", Longitude: " + longitude + ", Latitude: "
				+ latitude;
	}

	public Parking() {
		super();
	}

	public String toHtml() {
		String html= "<li>code = " + code + "<ul><li>address = " + address + "<li> bookableSpaces = " + bookableSpaces + "<li>totalSpaces = " + totalSpaces + "<li>longitude = " + longitude +"<li>latitude = " + latitude+"</ul>" ;
		
		return html;
	}
}
