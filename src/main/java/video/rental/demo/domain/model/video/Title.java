package video.rental.demo.domain.model.video;

import video.rental.demo.domain.shared.ValueObject;

public class Title implements ValueObject<Title>{
	private String title;
	
	public Title(final String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
	@Override
	public boolean sameValueAs(final Title other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
