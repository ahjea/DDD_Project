package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public class VideoID implements ValueObject<VideoID>{
	private String id;
	
	public VideoID(final String id) {
		this.id = id;
	}
	
	public String idString() {
		return id;
	}
	
	@Override
	public boolean sameValueAs(final VideoID other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
