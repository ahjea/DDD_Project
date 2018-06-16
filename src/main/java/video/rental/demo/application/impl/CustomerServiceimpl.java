package video.rental.demo.application.impl;

import java.util.ArrayList;
import java.util.List;

import video.rental.demo.application.CustomerService;
import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.customer.DateOfBirth;
import video.rental.demo.domain.model.customer.Name;
import video.rental.demo.domain.model.video.Rental;

public class CustomerServiceimpl implements CustomerService {
	
	CustomerRepository customerRepository;
	
	public CustomerServiceimpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#getCustomerRepository()
	 */
	@Override
	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#setCustomerRepository(video.rental.demo.domain.model.customer.CustomerRepository)
	 */
	@Override
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#clearRentals(java.lang.String)
	 */
	@Override
	public void clearRentals(String customerCode) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(customerCode));
	
		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			System.out.println("Id: " + foundCustomer.getCustomerID() + "\nName: " + foundCustomer.getName() + "\tRentals: "
					+ foundCustomer.getRentals().size());
			for (Rental rental : foundCustomer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
			}
	
			List<Rental> rentals = new ArrayList<Rental>();
			foundCustomer.setRentals(rentals);
	
			getCustomerRepository().saveCustomer(foundCustomer);
		}
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#getCustomerReport(java.lang.String)
	 */
	@Override
	public String getCustomerReport(String customerid) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(customerid));
		if (foundCustomer != null) {
			return foundCustomer.getReport();
		}
		else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#registerCustomer(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void registerCustomer(String name, String code, String dateOfBirth) {
		Customer customer = new Customer(new CustomerID(code), new Name(name), new DateOfBirth(dateOfBirth));
		getCustomerRepository().saveCustomer(customer);
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.CustomerService#listCustomers()
	 */
	@Override
	public void listCustomers() {
		List<Customer> customers = getCustomerRepository().findAllCustomers();
	
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
	
	
}
