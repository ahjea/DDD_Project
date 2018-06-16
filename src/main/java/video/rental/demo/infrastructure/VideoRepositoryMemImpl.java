package video.rental.demo.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.VideoRepository;

public class VideoRepositoryMemImpl implements VideoRepository {
	private Map<Title, Video> videos = new HashMap<>();

	@Override
	public Video findVideoByTitle(Title title) {
		// TODO Auto-generated method stub
		return videos.get(title);
	}

	@Override
	public List<Video> findAllVideos() {
		// TODO Auto-generated method stub
		return videos.values().stream().collect(Collectors.toList());
	}

	@Override
	public void saveVideo(Video video) {
		// TODO Auto-generated method stub
		videos.put(video.getTitle(), video);
	}
}
