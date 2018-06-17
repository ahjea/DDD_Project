package video.rental.demo.application;

import video.rental.demo.domain.model.customer.CustomerRepository;

public interface CustomerManagementService {

	void listCustomers();

	String getCustomerReport(String customerid);

	void registerCustomer(String name, String code, String dateOfBirth);
}