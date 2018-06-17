package video.rental.demo.domain.model.customer;

import video.rental.demo.domain.shared.ValueObject;

public class CustomerID implements ValueObject<CustomerID>{
	private int code;
	
	public CustomerID(final int code) {
		this.code = code;
	}
	
	public int getCustomerIDnumber() {
		return this.code;
	}
	
	@Override
	public boolean sameValueAs(final CustomerID other) {
		// TODO Auto-generated method stub
		return this.getCustomerIDnumber() == other.getCustomerIDnumber();
	}
}
