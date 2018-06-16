package video.rental.demo.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.Code;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Type;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.Rental;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoRepository;

public class Interactor {

	CustomerRepository customerRepository;
	VideoRepository videoRepository;
	
	public Interactor(CustomerRepository customerRepository, VideoRepository videoRepository) {
		this.customerRepository = customerRepository;
		this.videoRepository = videoRepository;
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}
	
	public VideoRepository getVideoRepository() {
		return videoRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void setVideoRepository(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public void clearRentals(String customerCode) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new Code(customerCode));
	
		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			System.out.println("Id: " + foundCustomer.getCode() + "\nName: " + foundCustomer.getName() + "\tRentals: "
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

	public void returnVideo(String customerCode, String videoTitle) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new Code(customerCode));
		if (foundCustomer == null)
			return;
	
		List<Rental> customerRentals = foundCustomer.getRentals();
	
		for (Rental rental : customerRentals) {
			if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
				Video video = rental.returnVideo();
				video.setRented(false);
				getVideoRepository().saveVideo(video);
				break;
			}
		}
	
		getCustomerRepository().saveCustomer(foundCustomer);
	}

	public void listVideos() {
		List<Video> videos = getVideoRepository().findAllVideos();
	
		for (Video video : videos) {
			System.out.println(
					"Video type: " + video.getVideoType() + 
					"\tPrice code: " + video.getPriceCode() + 
					"\tRating: " + video.getVideoRating() +
					"\tTitle: " + video.getTitle()
					); 
		}
	}
	
	public String getCustomerReport(String code) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new Code(code));
		if (foundCustomer != null) {
			return foundCustomer.getReport();
		}
		else {
			return null;
		}
	}

	public void rentVideo(String code, String videoTitle) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new Code(code));
		if (foundCustomer == null)
			return;
		
		Video foundVideo = getVideoRepository().findVideoByTitle(new Title(videoTitle));
	
		if (foundVideo == null)
			return;
	
		if (foundVideo.isRented() == true)
			return;
	
		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == true) {
			getVideoRepository().saveVideo(foundVideo);
			getCustomerRepository().saveCustomer(foundCustomer);
		} else {
			return;
		}
	}

	public void listCustomers() {
		List<Customer> customers = getCustomerRepository().findAllCustomers();
	
		for (Customer customer : customers) {
			System.out.println("ID: " + customer.getCode() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size());
			for (Rental rental : customer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
				System.out.println("\tReturn Status: " + rental.getStatus());
			}
		}
	}

	public void registerCustomer(String name, String code, String dateOfBirth) {
		Customer customer = new Customer(new Code(code), new Name(name), dateOfBirth);
		getCustomerRepository().saveCustomer(customer);
	}

	public void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating) {
		Date registeredDate = new Date();
		
		Rating rating;
		if (videoRating == 1) rating = Rating.TWELVE;
		else if (videoRating == 2) rating = Rating.FIFTEEN;
		else if (videoRating == 3) rating = Rating.EIGHTEEN;
		else throw new IllegalArgumentException("No such rating " + videoRating);
		
		Type type;
		if (videoType == 1) type = Type.VHS;
		else if (videoType == 2) type = Type.CD;
		else if (videoType == 3) type = Type.DVD;
		else throw new IllegalArgumentException("No such type " + videoType);
		
		PriceCode priceCode;
		if (videoPriceCode == 1) priceCode = PriceCode.REGULAR;
		else if (videoPriceCode == 2) priceCode = PriceCode.NEW_RELEASE;
		else if (videoPriceCode == 3) priceCode = PriceCode.CHILDREN;
		else throw new IllegalArgumentException("No such type " + videoPriceCode);
		
		Title title;
		title = new Title(videoTitle);
		
		Video video = new Video(title, type, priceCode, rating, registeredDate);
	
		getVideoRepository().saveVideo(video);
	}
}
