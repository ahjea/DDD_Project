package video.rental.demo.application.utils;

import java.util.Date;
import java.util.List;

import video.rental.demo.domain.model.Customer;
import video.rental.demo.domain.model.Rating;
import video.rental.demo.domain.model.Rental;
import video.rental.demo.domain.model.Repository;
import video.rental.demo.domain.model.Video;

public class SampleGenerator {

	public static void generateSamples(Repository repository) {
		Customer james = new Customer(0, "James", "1975-5-15");
		Customer brown = new Customer(1, "Brown", "2001-3-17");
		repository.saveCustomer(james);
		repository.saveCustomer(brown);
	
		Video v1 = new Video("V1", Video.CD, Video.REGULAR, Rating.FIFTEEN, new Date());
		v1.setRented(true);
		Video v2 = new Video("V2", Video.DVD, Video.NEW_RELEASE, Rating.TWELVE, new Date());
		v2.setRented(true);
		repository.saveVideo(v1);
		repository.saveVideo(v2);
	
		Rental r1 = new Rental(v1);
		Rental r2 = new Rental(v2);
	
		List<Rental> rentals = james.getRentals();
		rentals.add(r1);
		rentals.add(r2);
		james.setRentals(rentals);
		repository.saveCustomer(james);
	}

}
