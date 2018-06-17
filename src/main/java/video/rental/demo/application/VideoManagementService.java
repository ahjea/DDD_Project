package video.rental.demo.application;

import video.rental.demo.domain.model.video.VideoRepository;

public interface VideoManagementService {

	VideoRepository getVideoRepository();

	void setVideoRepository(VideoRepository videoRepository);

	void listVideos();

	void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating);

}