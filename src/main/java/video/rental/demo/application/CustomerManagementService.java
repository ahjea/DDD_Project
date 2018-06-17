package video.rental.demo.application;

import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.Name;

public interface CustomerManagementService {
	String listCustomers();
	String getCustomerReport(CustomerID customerId);
	void registerCustomer(Name name, DateOfBirth dateOfBirth);
}
