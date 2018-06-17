package video.rental.demo.presentation.impl;

import java.util.Scanner;

import video.rental.demo.presentation.UI;
import video.rental.demo.application.RentService;
import video.rental.demo.application.CustomerManagementService;
import video.rental.demo.application.VideoManagementService;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.VideoType;
import video.rental.demo.domain.model.video.VideoID;

public class CmdUI implements UI {
	
	private CustomerManagementService customerManagementService;
	private VideoManagementService videoManagementService;
	private RentService rentService;
	
	public CmdUI(CustomerManagementService customerManagementService, VideoManagementService videoManagementService, RentService rentService) {
		super();
		this.setCustomerManagementService(customerManagementService);
		this.setVideoManagementService(videoManagementService);
		this.setRentservice(rentService);
	}
	
	private static Scanner scanner = new Scanner(System.in);

	public void start() {
//		generator.generateSamples(this);

		boolean quit = false;
		while (!quit) {
			int command = getCommand();
			switch (command) {
			case 0:
				quit = true;
				break;
			case 1:
				listCustomers();
				break;
			case 2:
				listVideos();
				break;
			case 3:
				register("customer");
				break;
			case 4:
				register("video");
				break;
			case 5:
				rentVideo();
				break;
			case 6:
				returnVideo();
				break;
			case 7:
				getCustomerReport();
				break;
			case 8:
				clearRentals();
				break;
			default:
				break;
			}
		}
		System.out.println("Bye");
	}

	public void clearRentals() {
		System.out.println("Enter customer code: ");
		CustomerID customerID = new CustomerID(scanner.nextInt());

		System.out.println(getRentService().clearRentals(customerID));
	}

	public void returnVideo() {
		System.out.println("Enter customer code: ");
		CustomerID customerID = new CustomerID(scanner.nextInt());
		
		System.out.println("Enter video ID to return: ");
		VideoID videoID = new VideoID(scanner.next());
		
		getRentService().returnVideo(customerID, videoID);
	}

	public void listVideos() {
		System.out.println("List of videos");

		System.out.println(getVideoManagementService().listVideos(null));
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");

		System.out.println(getCustomerManagementService().listCustomers());
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer code: ");
		CustomerID id = new CustomerID(scanner.nextInt());

		String result = getCustomerManagementService().getCustomerReport(id);
		
		if (result == null) {
			System.out.println("No customer found");
		} else {
			System.out.println(result);
		}
	}

	public void rentVideo() {
		System.out.println("Enter customer code: ");
		CustomerID customerID = new CustomerID(scanner.nextInt());

		System.out.println("Enter video id to rent: ");
		VideoID VideoID = new VideoID(scanner.next());
		
		getRentService().rentVideo(customerID, VideoID);
	}

	public void register(String object) {
		
		if (object.equals("customer")) {
			System.out.println("Enter customer name: ");
			Name name = new Name(scanner.next());

			System.out.println("Enter customer id: ");
			CustomerID id = new CustomerID(scanner.nextInt());

			System.out.println("Enter customer birthday: ");
			DateOfBirth dateOfBirth = new DateOfBirth(scanner.next());

			getCustomerManagementService().registerCustomer(name, dateOfBirth);
		} else {
			System.out.println("Enter video title to register: ");
			Title title = new Title(scanner.next());
			
			System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
			int videoType = scanner.nextInt();
			
			VideoType type;
			if (videoType == 1) type = VideoType.VHS;
			else if (videoType == 2) type = VideoType.CD;
			else if (videoType == 3) type = VideoType.DVD;
			else throw new IllegalArgumentException("No such type " + videoType);

			System.out.println("Enter price code( 1 for Regular, 2 for New Release 3 for Children ):");
			int videoPriceCode = scanner.nextInt();
			
			PriceCode priceCode;
			if (videoPriceCode == 1) priceCode = PriceCode.REGULAR;
			else if (videoPriceCode == 2) priceCode = PriceCode.NEW_RELEASE;
			else if (videoPriceCode == 3) priceCode = PriceCode.CHILDREN;
			else throw new IllegalArgumentException("No such type " + videoPriceCode);
			
			System.out.println("Enter video rating( 1 for 12, 2 for 15, 3 for 18 ):");
			int videoRating = scanner.nextInt();

			Rating rating;
			if (videoRating == 1) rating = Rating.TWELVE;
			else if (videoRating == 2) rating = Rating.FIFTEEN;
			else if (videoRating == 3) rating = Rating.EIGHTEEN;
			else throw new IllegalArgumentException("No such rating " + videoRating);
			
			System.out.println("Enter video quantity:");
			int videoQuantity = scanner.nextInt();
			
			getVideoManagementService().registerVideo(title, type, priceCode, rating, videoQuantity);
		}
	}

	public int getCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt();

		return command;
	}

	public CustomerManagementService getCustomerManagementService() {
		return customerManagementService;
	}

	public void setCustomerManagementService(CustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}

	public VideoManagementService getVideoManagementService() {
		return videoManagementService;
	}

	public void setVideoManagementService(VideoManagementService videoManagementService) {
		this.videoManagementService = videoManagementService;
	}

	public RentService getRentService() {
		return rentService;
	}

	public void setRentservice(RentService rentService) {
		this.rentService = rentService;
	}

}
