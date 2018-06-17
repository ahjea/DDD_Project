package video.rental.demo.application.impl;

import java.util.ArrayList;
import java.util.List;

import video.rental.demo.application.RentService;
import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.Rental;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoRepository;

public class RentServiceImpl implements RentService {
	
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
	
	public void rentVideo(String code, String videoid) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(code));
		if (foundCustomer == null)
			return;
		
		Video foundVideo = getVideorepository().findVideoByID(new VideoID(videoid));
	
		if (foundVideo == null)
			return;
	
		if (foundVideo.isRented() == true)
			return;
	
		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == true) {
			getVideorepository().saveVideo(foundVideo);
			getCustomerRepository().saveCustomer(foundCustomer);
		} else {
			return;
		}
	}
	
	public void returnVideo(String customerCode, String videoTitle) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(customerCode));
		if (foundCustomer == null)
			return;
	
		List<Rental> customerRentals = foundCustomer.getRentals();
	
		for (Rental rental : customerRentals) {
			if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
				Video video = rental.returnVideo();
				video.setRented(false);
				getVideorepository().saveVideo(video);
				break;
			}
		}
	
		getCustomerRepository().saveCustomer(foundCustomer);
	}
	
	public void clearRentals(String customerCode) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(customerCode));
	
		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			System.out.println("Id: " + foundCustomer.getCustomerID() + "\nName: " + foundCustomer.getName() + "\tRentals: "
					+ foundCustomer.getRentals().size());
			for (Rental rental : foundCustomer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
			}
	
			List<Rental> rentals = new ArrayList<Rental>();
			foundCustomer.setRentals(rentals);
	
			getCustomerRepository().saveCustomer(foundCustomer);
		}
	}
}
