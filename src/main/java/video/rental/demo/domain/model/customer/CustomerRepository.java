package video.rental.demo.domain.model.customer;

import java.util.List;

public interface CustomerRepository {
	List<Customer> findAllCustomers();
	Customer findCustomerById(CustomerID code);
	void saveCustomer(Customer customer);
}