package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public class Name implements ValueObject<Name>{
	private String name;
	
	public Name(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean sameValueAs(final Name other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
