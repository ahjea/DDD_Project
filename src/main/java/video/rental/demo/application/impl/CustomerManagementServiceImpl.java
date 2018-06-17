package video.rental.demo.application.impl;

import java.util.List;

import video.rental.demo.application.CustomerManagementService;
import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.video.Rental;

public class CustomerManagementServiceImpl implements CustomerManagementService {

	private CustomerRepository customerrepository;
	
	public CustomerManagementServiceImpl(CustomerRepository customerrepository) {
		this.setCustomerrepository(customerrepository);
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerManagementService#listCustomers()
	 */
	@Override
	public void listCustomers() {
		List<Customer> customers = getCustomerrepository().findAllCustomers();
	
		for (Customer customer : customers) {
			System.out.println("ID: " + customer.getCustomerID() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size());
			for (Rental rental : customer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
				System.out.println("\tReturn Status: " + rental.getStatus());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerManagementService#getCustomerReport(java.lang.String)
	 */
	@Override
	public String getCustomerReport(String customerid) {
		Customer foundCustomer = getCustomerrepository().findCustomerById(new CustomerID(customerid));
		if (foundCustomer != null) {
			return foundCustomer.getReport();
		}
		else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerManagementService#registerCustomer(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void registerCustomer(String name, String code, String dateOfBirth) {
		Customer customer = new Customer(new CustomerID(code), new Name(name), new DateOfBirth(dateOfBirth));
		getCustomerrepository().saveCustomer(customer);
	}

	public CustomerRepository getCustomerrepository() {
		return customerrepository;
	}

	public void setCustomerrepository(CustomerRepository customerrepository) {
		this.customerrepository = customerrepository;
	}
	
	
}
