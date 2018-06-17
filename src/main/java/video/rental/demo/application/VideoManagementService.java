package video.rental.demo.application;

import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.VideoType;

public interface VideoManagementService {
	String listVideos();
	void registerVideo(Title videoTitle, VideoType videoType, PriceCode videoPriceCode, Rating videoRating);
}