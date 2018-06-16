package video.rental.demo.domain.shared;

public interface Entity<T> {
	
	boolean sameIdentityAs(T other);
}
