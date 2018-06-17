package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public enum VideoType implements ValueObject<VideoType>{
	VHS, CD, DVD;
	
	@Override
	public boolean sameValueAs(final VideoType other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
