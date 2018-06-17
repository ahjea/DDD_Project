package video.rental.demo.infrastructure.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.infrastructure.persistence.PersistenceManager;

public class CustomerRepositoryDBImpl implements CustomerRepository{

	// JPA EntityManager
	static EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

	static EntityManager getEm() {
		return em;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findCustomerById(int)
	 */
	@Override
	public Customer findCustomerById(CustomerID code) {
		CustomerRepositoryDBImpl.getEm().getTransaction().begin();
		Customer customer = CustomerRepositoryDBImpl.getEm().find(Customer.class, code);
		CustomerRepositoryDBImpl.getEm().getTransaction().commit();
		return customer;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findAllCustomers()
	 */
	@Override
	public List<Customer> findAllCustomers() {
		TypedQuery<Customer> query = CustomerRepositoryDBImpl.getEm().createQuery("SELECT c FROM Customer c", Customer.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#saveCustomer(video.rental.demo.Customer)
	 */
	@Override
	public void saveCustomer(Customer customer) {
		try {
			CustomerRepositoryDBImpl.getEm().getTransaction().begin();
			CustomerRepositoryDBImpl.getEm().persist(customer);
			CustomerRepositoryDBImpl.getEm().getTransaction().commit();
		} catch (PersistenceException e) {
			return;
		}
	}
}
