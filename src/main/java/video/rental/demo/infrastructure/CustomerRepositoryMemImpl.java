package video.rental.demo.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.Code;
import video.rental.demo.domain.model.customer.CustomerRepository;

public class CustomerRepositoryMemImpl implements CustomerRepository {	
	private Map<Code, Customer> customers = new HashMap<>();

	@Override
	public Customer findCustomerById(Code code) {
		// TODO Auto-generated method stub
		return customers.get(code);
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return customers.values().stream().collect(Collectors.toList());
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.put(customer.getCode(), customer);
	}
}
