package de.codedo.jetty;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

public class AppInitializer implements ServletContextListener
{
	private static final Logger LOG = Log.getLogger(AppInitializer.class);

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		try
		{
			LOG.info("beginning bootstrap ...");
			TimeUnit.SECONDS.sleep(20);
			LOG.info("... bootstrap done");
		}
		catch (InterruptedException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		LOG.info("contextDestroyed");
	}
}
