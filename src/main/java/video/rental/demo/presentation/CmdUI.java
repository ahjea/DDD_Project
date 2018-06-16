package video.rental.demo.presentation;

import java.util.Scanner;

import video.rental.demo.application.impl.Interactor;

/*
 * �뵒鍮� �뿊�꽭�뒪 遺꾨━
 * constructor瑜� �넻�븳 �뵒�렂�뜕�떆 �씤�젥
 * 鍮꾩쫰�땲�뒪 濡쒖쭅 遺�
 * */
public class CmdUI {
	Interactor interactor;
	
	public CmdUI(Interactor interactor) {
		super();
		this.interactor = interactor;
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
		String customerCode = scanner.next();

		interactor.clearRentals(customerCode);
	}

	public void returnVideo() {
		System.out.println("Enter customer code: ");
		String customerCode = scanner.next();
		
		System.out.println("Enter video title to return: ");
		String videoTitle = scanner.next();
		
		interactor.returnVideo(customerCode, videoTitle);
	}

	public void listVideos() {
		System.out.println("List of videos");

		interactor.listVideos();
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");

		interactor.listCustomers();
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer code: ");
		String code = scanner.next();

		String result = interactor.getCustomerReport(code);
		
		if (result == null) {
			System.out.println("No customer found");
		} else {
			System.out.println(result);
		}
	}

	public void rentVideo() {
		System.out.println("Enter customer code: ");
		String code = scanner.next();

		System.out.println("Enter video title to rent: ");
		String videoTitle = scanner.next();

		interactor.rentVideo(code, videoTitle);
	}

	public void register(String object) {
		if (object.equals("customer")) {
			System.out.println("Enter customer name: ");
			String name = scanner.next();

			System.out.println("Enter customer code: ");
			String code = scanner.next();

			System.out.println("Enter customer birthday: ");
			String dateOfBirth = scanner.next();

			interactor.registerCustomer(name, code, dateOfBirth);
		} else {
			System.out.println("Enter video title to register: ");
			String title = scanner.next();

			System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
			int videoType = scanner.nextInt();

			System.out.println("Enter price code( 1 for Regular, 2 for New Release 3 for Children ):");
			int priceCode = scanner.nextInt();

			System.out.println("Enter video rating( 1 for 12, 2 for 15, 3 for 18 ):");
			int videoRating = scanner.nextInt();
			
			interactor.registerVideo(title, videoType, priceCode, videoRating);
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

}
