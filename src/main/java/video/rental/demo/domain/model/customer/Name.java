package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public final class Name implements ValueObject<Name>{
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
		return other != null && this.name.equals(other.name);
	}
	
	public Name() {}	// for hibernate
}
