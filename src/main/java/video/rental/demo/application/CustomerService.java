package video.rental.demo.application;

import video.rental.demo.domain.model.customer.CustomerRepository;

public interface CustomerService {

	CustomerRepository getCustomerRepository();

	void setCustomerRepository(CustomerRepository customerRepository);

	void clearRentals(String customerCode);

	String getCustomerReport(String customerid);

	void registerCustomer(String name, String code, String dateOfBirth);

	void listCustomers();

}