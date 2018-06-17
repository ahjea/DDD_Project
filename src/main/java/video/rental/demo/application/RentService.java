package video.rental.demo.application;

public interface RentService {
	public void rentVideo(String code, String videoid);
	public void returnVideo(String customerCode, String videoTitle);
	public void clearRentals(String customerCode);
}