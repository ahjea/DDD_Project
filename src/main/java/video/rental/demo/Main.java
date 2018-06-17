package video.rental.demo;

import video.rental.demo.application.CustomerManagementService;
import video.rental.demo.application.RentService;
import video.rental.demo.application.VideoManagementService;
import video.rental.demo.application.impl.CustomerManagementServiceImpl;
import video.rental.demo.application.impl.RentServiceImpl;
import video.rental.demo.application.impl.VideoManagementServiceImpl;
import video.rental.demo.application.utils.SampleGenerator;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.VideoRepository;
import video.rental.demo.infrastructure.db.CustomerRepositoryDBImpl;
import video.rental.demo.infrastructure.db.VideoRepositoryDBImpl;
import video.rental.demo.presentation.UI;
import video.rental.demo.presentation.impl.CmdUI;
import video.rental.demo.presentation.impl.GraphicUI;

public class Main
{
	private static UI ui;
	
	public static void main(String[] args)
	{
		CustomerRepository customerRepository = new CustomerRepositoryDBImpl();
		VideoRepository videoRepository = new VideoRepositoryDBImpl();
	
		SampleGenerator.generateSamples(customerRepository, videoRepository);
		
		CustomerManagementService customerManagementService = new CustomerManagementServiceImpl(customerRepository);
		VideoManagementService VideoManagementService = new VideoManagementServiceImpl(videoRepository);
		RentService rentService = new RentServiceImpl(customerRepository, videoRepository);
		
		boolean useGUI = false;
		if (useGUI) {
			ui = new GraphicUI(customerManagementService, VideoManagementService, rentService);
		} else {
			ui = new CmdUI(customerManagementService, VideoManagementService, rentService);
		}
		
		ui.start();
	}
}


