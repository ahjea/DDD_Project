package video.rental.demo.domain.model;

import video.rental.demo.domain.shared.ValueObject;

public enum Rating implements ValueObject<Rating>{
	TWELVE, FIFTEEN, EIGHTEEN;

	@Override
	public boolean sameValueAs(final Rating other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
