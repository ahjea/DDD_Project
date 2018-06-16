package video.rental.demo.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoRepository;

public class VideoRepositoryDBImpl implements VideoRepository{

	// JPA EntityManager
	static EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

	static EntityManager getEm() {
		return em;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findVideoByTitle(java.lang.String)
	 */
	@Override
	public Video findVideoByTitle(String title) {
		CustomerRepositoryDBImpl.getEm().getTransaction().begin();
		Video video = CustomerRepositoryDBImpl.getEm().find(Video.class, title);
		CustomerRepositoryDBImpl.getEm().getTransaction().commit();
		return video;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#findAllVideos()
	 */
	@Override
	public List<Video> findAllVideos() {
		TypedQuery<Video> query = CustomerRepositoryDBImpl.getEm().createQuery("SELECT c FROM Video c", Video.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.Repository#saveVideo(video.rental.demo.Video)
	 */
	@Override
	public void saveVideo(Video video) {
		try {
			CustomerRepositoryDBImpl.getEm().getTransaction().begin();
			CustomerRepositoryDBImpl.getEm().persist(video);
			CustomerRepositoryDBImpl.getEm().getTransaction().commit();
		} catch (PersistenceException e) {
			return;
		}
		return;
	}

}
