package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public class VideoID implements ValueObject<VideoID>{
	private int id;
	
	public VideoID(final int id) {
		this.setId(id);
	}
	
	@Override
	public boolean sameValueAs(final VideoID other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
