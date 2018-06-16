package video.rental.demo.application;

import video.rental.demo.domain.model.video.VideoRepository;

public interface VideoService {

	VideoRepository getVideoRepository();

	void setVideoRepository(VideoRepository videoRepository);

	void listVideos();

	void registerVideo(String videoTitle, int videoType, int videoPriceCode, int videoRating);
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