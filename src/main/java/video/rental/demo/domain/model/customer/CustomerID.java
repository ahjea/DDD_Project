package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public class CustomerID implements ValueObject<CustomerID>{
	private String code;
	
	public CustomerID(final String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
	
	@Override
	public boolean sameValueAs(final CustomerID other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
