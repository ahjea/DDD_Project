package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public enum Type implements ValueObject<Rating>{
	VHS, CD, DVD;
	
	@Override
	public boolean sameValueAs(final Rating other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
