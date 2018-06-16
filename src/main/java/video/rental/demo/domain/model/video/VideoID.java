package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public class VideoID implements ValueObject<VideoID>{
	private int id;
	
	public VideoID(final int id) {
		this.id = id;
	}
	
	@Override
	public boolean sameValueAs(final VideoID other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
