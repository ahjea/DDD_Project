package video.rental.demo.domain.model.video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import video.rental.demo.domain.model.customer.Customer;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Video implements video.rental.demo.domain.shared.Entity<Video>{
	@Id
	private Title title;
	private Rating videoRating;
	private int priceCode;
	private VideoID videoID;

	public static final int REGULAR = 1;
	public static final int NEW_RELEASE = 2;
	public static final int CHILDREN = 3;

	private Type videoType;

	private Date registeredDate;
	private boolean rented;

	public Video() {}	// for hibernate

	public Video(Title title, Type videoType, int priceCode, Rating videoRating, Date registeredDate) {
		this.title = title; 
		this.videoType = videoType;
		this.setPriceCode(priceCode);
		this.videoRating = videoRating;
		this.registeredDate = registeredDate;
	}

	public int getLateReturnPointPenalty() {
		int pentalty = 0;
		switch (videoType) {
		case VHS:
			pentalty = 1; break;
		case CD:
			pentalty = 2; break;
		case DVD:
			pentalty = 3; break;
		}
		return pentalty;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public Title getTitle() {
		return title;
	}

	public Rating getVideoRating() {
		return videoRating;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public Type getVideoType() {
		return videoType;
	}

	public boolean rentFor(Customer customer) {
		if (!isUnderAge(customer)) {		
			setRented(true);
			Rental rental = new Rental(this);
			List<Rental> customerRentals = customer.getRentals();
			customerRentals.add(rental);
			customer.setRentals(customerRentals);
			return true;
		} else {
			return false;
		}
	}

	public boolean isUnderAge(Customer customer) {
		try {
			// calculate customer's age in years and months

			// parse customer date of birth
			Calendar calDateOfBirth = Calendar.getInstance();
			calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(customer.getDateOfBirth()));

			// get current date
			Calendar calNow = Calendar.getInstance();
			calNow.setTime(new java.util.Date());

			// calculate age different in years and months
			int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
			int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

			// decrement age in years if month difference is negative
			if (ageMo < 0) {
				ageYr--;
			}
			int age = ageYr;

			// determine if customer is under legal age for rating
			switch (videoRating) {
			case TWELVE:
				return age < 12;
			case FIFTEEN:
				return age < 15;
			case EIGHTEEN:
				return age < 18;
			default:
				return false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public VideoID getVideoID() {
		return videoID;
	}

	public void setVideoID(VideoID videoID) {
		this.videoID = videoID;
	}
	
	@Override
	public boolean sameIdentityAs(Video other) {
		// TODO Auto-generated method stub
		if(other.videoID == this.getVideoID()) return true;
		else return false;
	}

}
