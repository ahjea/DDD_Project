package video.rental.demo.domain.model.customer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoType;

@Entity
public class Rental implements video.rental.demo.domain.shared.Entity<Rental> {

	@Id
	@GeneratedValue
	private int id;

	private RentalStatus status; // 0 for Rented, 1 for Returned
	private Date rentDate;
	private Date returnDate;

	private VideoID videoID;

	Rental() {
	}

	public Rental(VideoID videoID) {
		this.videoID = videoID;
		status = RentalStatus.RENTED;
		rentDate = new Date();
	}

	public VideoID getVideoID() {
		return videoID;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public VideoID returnVideo() {
		if (status == RentalStatus.RENTED) {
			this.status = RentalStatus.RETURNED;
			returnDate = new Date();
		}
		return videoID;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit(VideoType type) {
		int limit = 0;
		switch (type) {
		case VHS:
			limit = 5;
			break;
		case CD:
			limit = 3;
			break;
		case DVD:
			limit = 2;
			break;
		}
		return limit;
	}

	public int getDaysRented() {
		long diff = 0;
		if (getStatus() == RentalStatus.RETURNED) { // returned Video
			diff = getReturnDate().getTime() - getRentDate().getTime();
		} else { // not yet returned
			diff = new Date().getTime() - getRentDate().getTime();
		}
		return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
	}

	@Override
	public boolean sameIdentityAs(Rental other) {
		// TODO Auto-generated method stub
		if(other.id == id) return true;
		else return false;
	}
}