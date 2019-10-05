package de.codedo.jetty;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		try
		{
			System.out.println("contextInitialized");
			System.out.println("Beginning bootstrap ...");
			TimeUnit.SECONDS.sleep(10);
			System.out.println("... bootstrap done");
		}
		catch (InterruptedException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		System.out.println("contextDestroyed");
	}
}
