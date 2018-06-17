package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public final class CustomerID implements ValueObject<CustomerID>{
	private String id;
	
	public CustomerID(final String code) {
		this.id = code;
	}

	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public boolean sameValueAs(final CustomerID other) {
		// TODO Auto-generated method stub
		return other != null && this.id.equals(other.id);
	}
	
	public CustomerID() {}	// for hibernate
}
