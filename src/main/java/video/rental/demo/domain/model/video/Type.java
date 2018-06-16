package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public enum Type implements ValueObject<Type>{
	VHS, CD, DVD;
	
	@Override
	public boolean sameValueAs(final Type other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
