package video.rental.demo.domain.model.video;

import java.util.List;

public interface VideoRepository {

	List<Video> findAllVideos();

	Video findVideoByTitle(Title title);

	void saveVideo(Video video);
	
}