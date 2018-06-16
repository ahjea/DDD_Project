package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public class Code implements ValueObject<Code>{
	private String code;
	
	public Code(final String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
	
	@Override
	public boolean sameValueAs(final Code other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
