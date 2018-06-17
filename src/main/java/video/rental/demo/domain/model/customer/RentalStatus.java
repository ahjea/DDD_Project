package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public enum RentalStatus implements ValueObject<RentalStatus>{
	RENTED, RETURNED;

	@Override
	public boolean sameValueAs(final RentalStatus other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
