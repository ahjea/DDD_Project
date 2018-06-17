package video.rental.demo.application;

public interface VideoManagementService {
	void listVideos();
	void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating);
}