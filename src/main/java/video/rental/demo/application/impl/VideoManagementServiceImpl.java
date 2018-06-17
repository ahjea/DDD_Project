package video.rental.demo.application.impl;

import java.util.Date;
import java.util.List;

import video.rental.demo.application.VideoManagementService;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.VideoType;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoRepository;

public class VideoManagementServiceImpl implements VideoManagementService {

	private VideoRepository videoRepository;
	
	public VideoManagementServiceImpl(VideoRepository videorepository) {
		this.setVideoRepository(videorepository);
	}

	public VideoRepository getVideoRepository() {
		return videoRepository;
	}

	public void setVideoRepository(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoManagementService#listVideos()
	 */
	@Override
	public String listVideos() {
		List<Video> videos = getVideoRepository().findAllVideos();
		
		String result = "";	
		for (Video video : videos) {
			result += "Video ID: "+ video.getVideoID() +
					"\tVideo type: " + video.getVideoType() + 
					"\tPrice code: " + video.getPriceCode() + 
					"\tRating: " + video.getVideoRating() +
					"\tTitle: " + video.getTitle() +
					"\n"; 
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoManagementService#registerVideo(java.lang.String, int, int, int)
	 */
	@Override
	public void registerVideo(Title videoTitle, VideoType videoType, PriceCode videoPriceCode, Rating videoRating) {
		Date registeredDate = new Date();
		
		int videoid = videoRepository.findAllVideos().size()+1;
		
		Video video = new Video(new VideoID(videoid), videoTitle, videoType, videoPriceCode, videoRating, registeredDate);
	
		getVideoRepository().saveVideo(video);
	}
	
}

