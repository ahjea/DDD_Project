package video.rental.demo;

import video.rental.demo.application.CustomerManagementService;
import video.rental.demo.application.RentService;
import video.rental.demo.application.impl.CustomerManagementServiceImpl;
import video.rental.demo.application.impl.RentServiceImpl;
import video.rental.demo.application.impl.VideoManagementServiceImpl;
import video.rental.demo.application.utils.SampleGenerator;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.VideoRepository;
import video.rental.demo.infrastructure.CustomerRepositoryDBImpl;
import video.rental.demo.infrastructure.VideoRepositoryDBImpl;
import video.rental.demo.presentation.impl.CmdUI;

public class Main
{
	private static CmdUI ui;
	
	public static void main(String[] args)
	{
		CustomerRepository customerRepository = new CustomerRepositoryDBImpl();
		VideoRepository videoRepository = new VideoRepositoryDBImpl();
	
		SampleGenerator.generateSamples(customerRepository, videoRepository);
		
		CustomerManagementServiceImpl customermanagementserviceimpl = new CustomerManagementServiceImpl(customerRepository);
		VideoManagementServiceImpl VideoManagementServiceImpl = new VideoManagementServiceImpl(videoRepository);
		RentServiceImpl rentserviceimpl = new RentServiceImpl(customerRepository, videoRepository);
		
		ui = new CmdUI(customermanagementserviceimpl, VideoManagementServiceImpl, rentserviceimpl);
		
		ui.start();
	}
}


