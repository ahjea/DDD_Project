package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public enum PriceCode implements ValueObject<PriceCode>{
	REGULAR, NEW_RELEASE, CHILDREN;
	
	@Override
	public boolean sameValueAs(final PriceCode other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
