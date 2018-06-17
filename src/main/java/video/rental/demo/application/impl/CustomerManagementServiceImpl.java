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
	public String listCustomers() {
		List<Customer> customers = getCustomerrepository().findAllCustomers();
	
		String result = "";
		for (Customer customer : customers) {
			result += "ID: " + customer.getCustomerID() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size() + "\n";
			for (Rental rental : customer.getRentals()) {
				result += "\tTitle: " + rental.getVideo().getTitle() + " ";
				result += "\tPrice Code: " + rental.getVideo().getPriceCode();
				result += "\tReturn Status: " + rental.getStatus() + "\n";
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerManagementService#getCustomerReport(java.lang.String)
	 */
	@Override
	public String getCustomerReport(CustomerID customerid) {
		Customer foundCustomer = getCustomerrepository().findCustomerById(customerid);
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
	public void registerCustomer(Name name, CustomerID id, DateOfBirth dateOfBirth) {
		Customer customer = new Customer(id, name, dateOfBirth);
		getCustomerrepository().saveCustomer(customer);
	}

	public CustomerRepository getCustomerrepository() {
		return customerrepository;
	}

	public void setCustomerrepository(CustomerRepository customerrepository) {
		this.customerrepository = customerrepository;
	}
	
	
}
