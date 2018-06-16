package video.rental.demo.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import video.rental.demo.domain.model.Customer;
import video.rental.demo.domain.model.Repository;
import video.rental.demo.domain.model.Video;

public class RepositoryDBImpl implements Repository {

	// JPA EntityManager
	static EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

	static EntityManager getEm() {
		return em;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findCustomerById(int)
	 */
	@Override
	public Customer findCustomerById(int code) {
		RepositoryDBImpl.getEm().getTransaction().begin();
		Customer customer = RepositoryDBImpl.getEm().find(Customer.class, code);
		RepositoryDBImpl.getEm().getTransaction().commit();
		return customer;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findVideoByTitle(java.lang.String)
	 */
	@Override
	public Video findVideoByTitle(String title) {
		RepositoryDBImpl.getEm().getTransaction().begin();
		Video video = RepositoryDBImpl.getEm().find(Video.class, title);
		RepositoryDBImpl.getEm().getTransaction().commit();
		return video;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findAllCustomers()
	 */
	@Override
	public List<Customer> findAllCustomers() {
		TypedQuery<Customer> query = RepositoryDBImpl.getEm().createQuery("SELECT c FROM Customer c", Customer.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findAllVideos()
	 */
	@Override
	public List<Video> findAllVideos() {
		TypedQuery<Video> query = RepositoryDBImpl.getEm().createQuery("SELECT c FROM Video c", Video.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#saveCustomer(video.rental.demo.Customer)
	 */
	@Override
	public void saveCustomer(Customer customer) {
		try {
			RepositoryDBImpl.getEm().getTransaction().begin();
			RepositoryDBImpl.getEm().persist(customer);
			RepositoryDBImpl.getEm().getTransaction().commit();
		} catch (PersistenceException e) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#saveVideo(video.rental.demo.Video)
	 */
	@Override
	public void saveVideo(Video video) {
		try {
			RepositoryDBImpl.getEm().getTransaction().begin();
			RepositoryDBImpl.getEm().persist(video);
			RepositoryDBImpl.getEm().getTransaction().commit();
		} catch (PersistenceException e) {
			return;
		}
		return;
	}

}
