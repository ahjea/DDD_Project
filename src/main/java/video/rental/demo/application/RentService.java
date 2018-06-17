package video.rental.demo.application;

public interface RentService {
	void rentVideo(String code, int videoid);
	void returnVideo(String customerCode, String videoTitle);
	String clearRentals(String customerCode);
}