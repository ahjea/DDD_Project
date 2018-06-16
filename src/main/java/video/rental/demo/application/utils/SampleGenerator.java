package video.rental.demo.application.utils;

import java.util.Date;
import java.util.List;

import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Type;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.Rental;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoRepository;

public class SampleGenerator {

	public static void generateSamples(CustomerRepository customerRepository, VideoRepository videoRepository) {
		Customer james = new Customer(new CustomerID("0"), new Name("James"), new DateOfBirth("1975-5-15"));
		Customer brown = new Customer(new CustomerID("1"), new Name("Brown"), new DateOfBirth("2001-3-17"));
		customerRepository.saveCustomer(james);
		customerRepository.saveCustomer(brown);
	
		Video v1 = new Video(new Title("V1"), Type.CD, PriceCode.REGULAR, Rating.FIFTEEN, new Date());
		v1.setRented(true);
		Video v2 = new Video(new Title("V2"), Type.DVD, PriceCode.NEW_RELEASE, Rating.TWELVE, new Date());
		v2.setRented(true);
		videoRepository.saveVideo(v1);
		videoRepository.saveVideo(v2);
	
		Rental r1 = new Rental(v1);
		Rental r2 = new Rental(v2);
	
		List<Rental> rentals = james.getRentals();
		rentals.add(r1);
		rentals.add(r2);
		james.setRentals(rentals);
		customerRepository.saveCustomer(james);
	}

}
