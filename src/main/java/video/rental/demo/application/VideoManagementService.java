package video.rental.demo.application;

import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.Type;

public interface VideoManagementService {
	String listVideos();
	void registerVideo(Title videoTitle, Type videoType, PriceCode videoPriceCode, Rating videoRating);
}