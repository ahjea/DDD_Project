package video.rental.demo.application;

import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.video.VideoID;

public interface RentService {
	void rentVideo(CustomerID customerID, VideoID videoID);
	void returnVideo(CustomerID customerID, VideoID videoID);
	String clearRentals(CustomerID customerID);
}