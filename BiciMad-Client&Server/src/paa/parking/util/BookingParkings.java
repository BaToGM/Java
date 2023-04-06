package paa.parking.util;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Vector;

import paa.parking.business.BookingService;
import paa.parking.business.IBookingService;
import paa.parking.model.Booking;

public class BookingParkings {
	public static Collection<Booking> listBookingParkings() {
		Vector<Booking> bookings = new Vector<>();
		IBookingService s = new BookingService ();
		//(String licencePlate, LocalDate date, Parking parking)
		bookings.add(new Booking(null, "1234AAA", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "1234AAB", LocalDate.now().plusDays(7), s.findParking((long) 1)));
		bookings.add(new Booking(null, "2325AAb", LocalDate.now().plusDays(3), s.findParking((long) 2)));
		bookings.add(new Booking(null, "9874VVV", LocalDate.now().plusDays(5), s.findParking((long) 3)));
		bookings.add(new Booking(null, "8754RTE", LocalDate.now().plusDays(3), s.findParking((long) 3)));
		bookings.add(new Booking(null, "3698ADF", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "1234QWE", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "1234POT", LocalDate.now().plusDays(6), s.findParking((long) 2)));
		bookings.add(new Booking(null, "9876AAA", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "4443AAA", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "2345AAA", LocalDate.now().plusDays(7), s.findParking((long) 3)));
		bookings.add(new Booking(null, "1234QAZ", LocalDate.now(), s.findParking((long) 4)));
		bookings.add(new Booking(null, "1234ZAQ", LocalDate.now(), s.findParking((long) 2)));
		bookings.add(new Booking(null, "1234WSX", LocalDate.now().plusDays(8), s.findParking((long) 1)));
		bookings.add(new Booking(null, "2345RAA", LocalDate.now(), s.findParking((long) 3)));
		bookings.add(new Booking(null, "3454AAA", LocalDate.now(), s.findParking((long) 3)));
		bookings.add(new Booking(null, "4567FAA", LocalDate.now().plusDays(3), s.findParking((long) 1)));
		bookings.add(new Booking(null, "6789POI", LocalDate.now(), s.findParking((long) 3)));
		bookings.add(new Booking(null, "7890OIU", LocalDate.now().plusDays(7), s.findParking((long) 3)));
		bookings.add(new Booking(null, "2345IUY", LocalDate.now(), s.findParking((long) 2)));
		bookings.add(new Booking(null, "7654UYT", LocalDate.now(), s.findParking((long) 2)));
		bookings.add(new Booking(null, "0987ÑLK", LocalDate.now(), s.findParking((long) 2)));
		bookings.add(new Booking(null, "9876LKJ", LocalDate.now().plusDays(12), s.findParking((long) 5)));
		bookings.add(new Booking(null, "8765LKJ", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "7654KJH", LocalDate.now().plusDays(11), s.findParking((long) 6)));
		bookings.add(new Booking(null, "6543JHG", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "5432MNB", LocalDate.now().plusDays(14), s.findParking((long) 7)));
		bookings.add(new Booking(null, "8525NBB", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "5558NNB", LocalDate.now().plusDays(8), s.findParking((long) 8)));
		bookings.add(new Booking(null, "1564ASQ", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "4895HGB", LocalDate.now().plusDays(4), s.findParking((long) 9)));
		bookings.add(new Booking(null, "4875QWE", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "1235SDV", LocalDate.now().plusDays(1), s.findParking((long) 10)));
		bookings.add(new Booking(null, "8596ASD", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "5558NNB", LocalDate.now().plusDays(2), s.findParking((long) 11)));
		bookings.add(new Booking(null, "4981ASD", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "3648FGH", LocalDate.now().plusDays(4), s.findParking((long) 12)));
		bookings.add(new Booking(null, "9614RGH", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "1348UIR", LocalDate.now().plusDays(2), s.findParking((long) 13)));
		bookings.add(new Booking(null, "9658IOY", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "0145QWE", LocalDate.now().plusDays(3), s.findParking((long) 14)));
		bookings.add(new Booking(null, "3612KJE", LocalDate.now(), s.findParking((long) 1)));
		bookings.add(new Booking(null, "3615JLO", LocalDate.now().plusDays(2), s.findParking((long) 15)));
		bookings.add(new Booking(null, "4870POQ", LocalDate.now(), s.findParking((long) 1)));

		return bookings;
	}
}
