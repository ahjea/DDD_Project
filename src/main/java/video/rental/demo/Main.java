package video.rental.demo;

import video.rental.demo.application.impl.Interactor;
import video.rental.demo.application.utils.SampleGenerator;
import video.rental.demo.domain.model.customer.CustomerRepository;
import video.rental.demo.domain.model.video.VideoRepository;
import video.rental.demo.infrastructure.CustomerRepositoryDBImpl;
import video.rental.demo.infrastructure.VideoRepositoryDBImpl;
import video.rental.demo.presentation.CmdUI;

public class Main
{
	private static CmdUI ui;
	
	public static void main(String[] args)
	{
		CustomerRepository customerRepository = new CustomerRepositoryDBImpl();
		VideoRepository videoRepository = new VideoRepositoryDBImpl();
	
		SampleGenerator.generateSamples(customerRepository, videoRepository);
		
		Interactor interactor = new Interactor(customerRepository, videoRepository);
		
		ui = new CmdUI(interactor);
		
		ui.start();
	}
}


