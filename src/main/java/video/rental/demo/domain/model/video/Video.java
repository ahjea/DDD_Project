package video.rental.demo.domain.model.video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import video.rental.demo.domain.model.customer.Customer;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "videoID" }) })
public class Video implements video.rental.demo.domain.shared.Entity<Video>{
	@Id
	private VideoID videoID;

	private Title title;
	private Rating videoRating;
	private PriceCode priceCode;

	private VideoType videoType;

	private Date registeredDate;
	private boolean rented;
	
	public Video() {}	// for hibernate

	public Video(VideoID videoID, Title title, VideoType videoType, PriceCode priceCode, Rating videoRating, Date registeredDate) {
		this.videoID = videoID;
		this.title = title; 
		this.videoType = videoType;
		this.priceCode = priceCode;
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

	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public Title getTitle() {
		return title;
	}
	
	public VideoID getID() {
		return videoID;
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

	public VideoType getVideoType() {
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
			calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(customer.getDateOfBirth().toString()));

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
