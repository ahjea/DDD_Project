package video.rental.demo.domain.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import video.rental.demo.domain.model.video.Rental;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.PriceCode;

@Entity
public class Customer implements video.rental.demo.domain.shared.Entity<Customer> {
	@Id
	private Code code;
	private String name;
	private String dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer() {	// for hibernate
	}

	public Customer(Code code, String name, String dateOfBirth) {
		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Code getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0;
			int daysRented = 0;

			daysRented = each.getDaysRented();

			switch (each.getVideo().getPriceCode()) {
			case REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			case CHILDREN:
				eachCharge += 1.5;
				if (daysRented > 3)
					eachCharge += (daysRented - 3) * 1.5;
				break;
			}
			
			eachPoint++;
			if ((each.getVideo().getPriceCode() == PriceCode.NEW_RELEASE))
				eachPoint++;

			if (daysRented > each.getDaysRentedLimit())
				eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty());

			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;
			totalPoint += eachPoint;
		}
		
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		if (totalPoint >= 10) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if (totalPoint >= 30) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result;
	}

	@Override
	public boolean sameIdentityAs(Customer other) {
		// TODO Auto-generated method stub
		if(other.code == this.getCode()) return true;
		else return false;
	}

}
