package video.rental.demo.application;

public interface CustomerManagementService {
	void listCustomers();
	String getCustomerReport(String customerid);
	void registerCustomer(String name, String code, String dateOfBirth);
}