package video.rental.demo.presentation.impl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;

import video.rental.demo.application.CustomerManagementService;
import video.rental.demo.application.RentService;
import video.rental.demo.application.VideoManagementService;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoType;
import video.rental.demo.presentation.UI;

import javax.swing.JSpinner;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.Font;

@SuppressWarnings("serial")
public class GraphicUI extends JFrame implements UI {
	
	private CustomerManagementService customerManagementService;
	private VideoManagementService videoManagementService;
	private RentService rentService;

	private JTextField userCodeField;
	private JTextField nameField;
	private JTextField birthdayField;

	private JTextField idField;
	private JTextField titleField;
	private JTextField quantityField;

	private JSpinner priceCodeSpinner;
	private JSpinner videoTypeSpinner;
	private JSpinner ratingSpinner;

	private JTextArea textArea;

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicUI(CustomerManagementService customerManagementService, VideoManagementService videoManagementService, RentService rentService) {
		initialize();
		this.customerManagementService = customerManagementService;
		this.videoManagementService = videoManagementService;
		this.rentService = rentService;
	}

	/**
	 * Initialize the contents of the
	 */
	private void initialize() {
		setBounds(100, 100, 1200, 522);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		textArea = new JTextArea(6, 80);
		textArea.setEditable(false);
		textArea.setVisible(true);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(18, 249, 900, 133);
		getContentPane().add(scroll);

		JLabel lblWelcomeToSs = new JLabel("Welcome To Premier Video Shop");
		lblWelcomeToSs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblWelcomeToSs.setBounds(208, 20, 210, 16);
		getContentPane().add(lblWelcomeToSs);

		makeButton("Register User", (e) -> registerUser(), 18, 54, 117, 29);

		makeLabel("User Code:", 147, 59, 70, 16);

		userCodeField = new JTextField();
		userCodeField.setBounds(217, 54, 50, 26);
		getContentPane().add(userCodeField);
		userCodeField.setColumns(10);

		makeLabel("Name:", 280, 59, 61, 16);

		nameField = new JTextField();
		nameField.setBounds(324, 54, 120, 26);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		makeLabel("Birthday:", 462, 59, 60, 16);

		birthdayField = new JTextField();
		birthdayField.setBounds(520, 54, 96, 26);
		getContentPane().add(birthdayField);
		birthdayField.setColumns(10);

		makeButton("Register Video", (e) -> registerVideo(), 18, 95, 117, 29);

		makeLabel("ID:", 147, 100, 61, 16);

		idField = new JTextField();
		idField.setBounds(182, 95, 130, 26);
		getContentPane().add(idField);
		idField.setColumns(10);

		makeLabel("Title:", 347, 100, 61, 16);

		titleField = new JTextField();
		titleField.setBounds(382, 95, 100, 26);
		getContentPane().add(titleField);
		titleField.setColumns(10);

		makeLabel("Price Code:", 494, 100, 75, 16);

		String[] priceCodes = new String[] { "Regular", "New", "Children" };
		SpinnerListModel priceCodeModel = new SpinnerListModel(priceCodes);
		priceCodeSpinner = new JSpinner(priceCodeModel);
		priceCodeSpinner.setBounds(562, 95, 75, 26);
		getContentPane().add(priceCodeSpinner);

		makeLabel("Type:", 645, 100, 61, 16);

		String[] videoTypes = new String[] { "VHS", "CD", "DVD" };
		SpinnerListModel videoTypeModel = new SpinnerListModel(videoTypes);
		videoTypeSpinner = new JSpinner(videoTypeModel);
		videoTypeSpinner.setBounds(680, 95, 55, 26);
		getContentPane().add(videoTypeSpinner);

		makeLabel("Rating:", 744, 100, 61, 16);

		String[] videoRating = new String[] { "Twelve", "Fifteen", "Eighteen" };
		SpinnerListModel videoRatingModel = new SpinnerListModel(videoRating);
		ratingSpinner = new JSpinner(videoRatingModel);
		ratingSpinner.setBounds(790, 95, 70, 26);
		getContentPane().add(ratingSpinner);
		
		makeLabel("Quantity:", 900, 100, 61, 16);
		quantityField = new JTextField();
		quantityField.setBounds(1000, 95, 90, 26);
		getContentPane().add(quantityField);
		quantityField.setColumns(10);
		
		makeSeparator(18, 85, 583, 16);
		makeSeparator(18, 128, 583, 16);

		makeButton("Rent", (e) -> rentVideo(), 18, 148, 117, 29);
		makeButton("Return", (e) -> returnVideo(), 136, 148, 117, 29);

		makeSeparator(18, 176, 583, 16);

		makeButton("List Customers", (e) -> listCustomers(), 18, 193, 130, 29);
		makeButton("List Videos", (e) -> listVideos(), 146, 193, 117, 29);
		makeButton("Customer Report", (e) -> getCustomerReport(), 297, 193, 138, 29);
		makeButton("Clear Customer Rentals", (e) -> clearRentals(), 427, 193, 174, 29);
		makeButton("Clear", (e) -> clear(), 484, 149, 117, 29);
	}

	private void clear() {
		nameField.setText("");
		userCodeField.setText("");
		titleField.setText("");
		textArea.setText("");
	}

	private void clearRentals()
	{
		CustomerID id = new CustomerID(userCodeField.getText().toString());

		String result = rentService.clearRentals(id);
		
		textArea.append(result);
	}

	private void getCustomerReport() {
		CustomerID id = new CustomerID(userCodeField.getText().toString());

		String result = customerManagementService.getCustomerReport(id);

		textArea.append(result);
	}

	private void listVideos() {
		String titleStr =  titleField.getText().toString();
		Title title;
		System.out.println(titleStr);
		if (titleStr.length() == 0) {
			title = null;
		} else {
			title = new Title(titleStr);
		}
		textArea.append("List of videos\n");
		textArea.append(videoManagementService.listVideos(title));
		textArea.append("End of list\n");
	}

	private void listCustomers() {
		textArea.append("List of customers\n");
		textArea.append(customerManagementService.listCustomers());
		textArea.append("End of list\n");
	}

	private void returnVideo() {
		CustomerID customerID = new CustomerID(userCodeField.getText().toString());
		VideoID videoID = new VideoID(idField.getText().toString());

		rentService.returnVideo(customerID, videoID);
	}

	private void rentVideo() {
		CustomerID customerID = new CustomerID(userCodeField.getText().toString());
		VideoID videoID = new VideoID(idField.getText().toString());

		rentService.rentVideo(customerID, videoID);
	}

	private void registerUser() {
		CustomerID id = new CustomerID(userCodeField.getText().toString());
		Name name = new Name(nameField.getText().toString());
		DateOfBirth birthday = new DateOfBirth(birthdayField.getText().toString());

		customerManagementService.registerCustomer(name, id, birthday);
	}

	private void registerVideo() {
		Title title = new Title(titleField.getText().toString());
		String videoTypeString = videoTypeSpinner.getValue().toString();
		VideoType videoType;
		if (videoTypeString.equals("VHS"))
			videoType = VideoType.VHS;
		else if (videoTypeString.equals("CD"))
			videoType = VideoType.CD;
		else // DVD
			videoType = VideoType.DVD;

		String priceCodeString = priceCodeSpinner.getValue().toString();
		PriceCode priceCode;
		if (priceCodeString.equals("Regular"))
			priceCode = PriceCode.REGULAR;
		else if (priceCodeString.equals("New"))
			priceCode = PriceCode.NEW_RELEASE;
		else // Children
			priceCode = PriceCode.CHILDREN;

		String ratingString = ratingSpinner.getValue().toString();
		Rating videoRating;
		if (ratingString.equals("Twelve"))
			videoRating = Rating.TWELVE;
		else if (ratingString.equals("Fifteen"))
			videoRating = Rating.FIFTEEN;
		else // Eighteen
			videoRating = Rating.EIGHTEEN;
		
		int quantity =  Integer.parseInt(quantityField.getText().toString());
		
		videoManagementService.registerVideo(title, videoType, priceCode, videoRating, quantity);
	}

	private void makeButton(String title, ActionListener listener, int x, int y, int w, int h) {
		JButton button = new JButton(title);
		button.addActionListener(listener);
		button.setBounds(x, y, w, h);
		getContentPane().add(button);
	}

	private void makeLabel(String title, int x, int y, int w, int h) {
		JLabel label = new JLabel(title);
		label.setBounds(x, y, w, h);
		getContentPane().add(label);
	}

	private void makeSeparator(int x, int y, int w, int h) {
		JSeparator separator = new JSeparator();
		separator.setBounds(x, y, w, h);
		getContentPane().add(separator);
	}
}
