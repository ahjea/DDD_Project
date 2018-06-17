package video.rental.demo.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.rental.demo.application.RentService;
import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.customer.Rental;
import video.rental.demo.domain.model.customer.RentalStatus;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoRepository;

public class RentServiceImpl implements RentService {
	
	private Map<VideoID, CustomerID> rentLog = new HashMap<>();
	private CustomerRepository customerRepository;
	private VideoRepository videorepository;
	
	public RentServiceImpl(CustomerRepository customerRepository, VideoRepository videorepository) {
		this.setCustomerRepository(customerRepository);
		this.setVideorepository(videorepository);
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public VideoRepository getVideorepository() {
		return videorepository;
	}

	public void setVideorepository(VideoRepository videorepository) {
		this.videorepository = videorepository;
	}
	
	public void rentVideo(CustomerID customerID, VideoID videoID) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(customerID);
		if (foundCustomer == null)
			return;
		
		Video foundVideo = getVideorepository().findVideoByID(videoID);
	
		if (foundVideo == null)
			return;
	
		if (foundVideo.isRented() == true)
			return;
	
		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == false) {
			return;
		}
		
		CustomerID locked = rentLog.putIfAbsent(videoID, customerID);
		if (locked != null) {
			return;
		}
		
		getVideorepository().saveVideo(foundVideo);
		getCustomerRepository().saveCustomer(foundCustomer);
	
		rentLog.remove(videoID);
	}
	
	public void returnVideo(CustomerID customerID, VideoID videoID) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(customerID);
		if (foundCustomer == null)
			return;
	
		List<Rental> customerRentals = foundCustomer.getRentals();
	
		for (Rental rental : customerRentals) {
			if (rental.getVideoID().sameValueAs(videoID)) {
				Video video = videorepository.findVideoByID(videoID);
				if (video.isRented() == false) {
					continue;
				}
				
				video.setRented(false);
				getVideorepository().saveVideo(video);
				break;
			}
		}
	
		getCustomerRepository().saveCustomer(foundCustomer);
	}
	
	public String clearRentals(CustomerID customerID) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(customerID);
	
		String result = "";
		if (foundCustomer == null) {
			result += "No customer found\n";
		} else {
			result += "Id: " + foundCustomer.getCustomerID() + "\nName: " + foundCustomer.getName() + "\tRentals: "
					+ foundCustomer.getRentals().size() + "\n";
			for (Rental rental : foundCustomer.getRentals()) {
				Video video = videorepository.findVideoByID(rental.getVideoID());
				result += "\tTitle: " + video.getTitle() + " ";
				result += "\tPrice Code: " + video.getPriceCode();
				
				if (rental.getStatus() == RentalStatus.RENTED) {
					returnVideo(customerID, rental.getVideoID());
				}
			}
	
			List<Rental> rentals = new ArrayList<Rental>();
			foundCustomer.setRentals(rentals);
	
			getCustomerRepository().saveCustomer(foundCustomer);
		}
		
		return result;
	}
}
