package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public final class VideoID implements ValueObject<VideoID>{
	private String id;
	
	public VideoID(final String id) {
		this.id = id;
	}
	
	public VideoID(final int id) {
		this.id = String.valueOf(id);
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public boolean sameValueAs(final VideoID other) {
		// TODO Auto-generated method stub
		return other != null && this.id.equals(other.id);
	}
	
	public VideoID() {}	// for hibernate
}
