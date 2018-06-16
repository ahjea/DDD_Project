package video.rental.demo.application.impl;

import java.util.Date;
import java.util.List;

import video.rental.demo.application.VideoService;
import video.rental.demo.domain.model.customer.Customer;
import video.rental.demo.domain.model.customer.CustomerID;
import video.rental.demo.domain.model.video.PriceCode;
import video.rental.demo.domain.model.video.Rating;
import video.rental.demo.domain.model.video.Rental;
import video.rental.demo.domain.model.video.Title;
import video.rental.demo.domain.model.video.Type;
import video.rental.demo.domain.model.video.Video;
import video.rental.demo.domain.model.video.VideoID;
import video.rental.demo.domain.model.video.VideoRepository;

public class VideoServiceimpl implements VideoService {

	VideoRepository videoRepository;
	
	public VideoServiceimpl(VideoRepository videorepository) {
		this.videoRepository = videorepository;
	}

	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoService#getVideoRepository()
	 */
	@Override
	public VideoRepository getVideoRepository() {
		return videoRepository;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoService#setVideoRepository(video.rental.demo.domain.model.video.VideoRepository)
	 */
	@Override
	public void setVideoRepository(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoService#listVideos()
	 */
	@Override
	public void listVideos() {
		List<Video> videos = getVideoRepository().findAllVideos();
	
		for (Video video : videos) {
			System.out.println(
					"Video type: " + video.getVideoType() + 
					"\tPrice code: " + video.getPriceCode() + 
					"\tRating: " + video.getVideoRating() +
					"\tTitle: " + video.getTitle()
					); 
		}
	}
	
	/* (non-Javadoc)
	 * @see video.rental.demo.application.impl.VideoService#registerVideo(java.lang.String, int, int, int)
	 */
	@Override
	public void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating) {
		Date registeredDate = new Date();
		
		Rating rating;
		if (videoRating == 1) rating = Rating.TWELVE;
		else if (videoRating == 2) rating = Rating.FIFTEEN;
		else if (videoRating == 3) rating = Rating.EIGHTEEN;
		else throw new IllegalArgumentException("No such rating " + videoRating);
		
		Type type;
		if (videoType == 1) type = Type.VHS;
		else if (videoType == 2) type = Type.CD;
		else if (videoType == 3) type = Type.DVD;
		else throw new IllegalArgumentException("No such type " + videoType);
		
		PriceCode priceCode;
		if (videoPriceCode == 1) priceCode = PriceCode.REGULAR;
		else if (videoPriceCode == 2) priceCode = PriceCode.NEW_RELEASE;
		else if (videoPriceCode == 3) priceCode = PriceCode.CHILDREN;
		else throw new IllegalArgumentException("No such type " + videoPriceCode);
		
		Title title;
		title = new Title(videoTitle);
		
		Video video = new Video(title, type, priceCode, rating, registeredDate);
	
		getVideoRepository().saveVideo(video);
	}
	/*
	//duplicated
	public void returnVideo(String customerCode, String videoTitle) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(customerCode));
		if (foundCustomer == null)
			return;
	
		List<Rental> customerRentals = foundCustomer.getRentals();
	
		for (Rental rental : customerRentals) {
			if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
				Video video = rental.returnVideo();
				video.setRented(false);
				getVideoRepository().saveVideo(video);
				break;
			}
		}
	
		getCustomerRepository().saveCustomer(foundCustomer);
	}
	
	//duplicated
	public void rentVideo(String code, String videoid) {
		Customer foundCustomer = getCustomerRepository().findCustomerById(new CustomerID(code));
		if (foundCustomer == null)
			return;
		
		Video foundVideo = getVideoRepository().findVideoByID(new VideoID(videoid));
	
		if (foundVideo == null)
			return;
	
		if (foundVideo.isRented() == true)
			return;
	
		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == true) {
			getVideoRepository().saveVideo(foundVideo);
			getCustomerRepository().saveCustomer(foundCustomer);
		} else {
			return;
		}
	}
	
	*/
}

