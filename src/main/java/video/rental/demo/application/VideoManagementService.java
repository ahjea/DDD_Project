package video.rental.demo.application;

public interface VideoManagementService {
	String listVideos();
	void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating);
}