package video.rental.demo.domain.model.video;

import java.util.List;

public interface VideoRepository {

	List<Video> findAllVideos();

	Video findVideoByID(VideoID videoid);

	void saveVideo(Video video);
	
}