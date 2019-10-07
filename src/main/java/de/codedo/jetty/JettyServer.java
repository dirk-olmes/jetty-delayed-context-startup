package de.codedo.jetty;

import java.io.File;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;

public class JettyServer
{
	private static final Logger LOG = Log.getLogger(JettyServer.class);

	public static void main(String[] args) throws Exception
	{
		Server server = new Server(8181);

		ContextHandler duringStartup = new DuringStartupHandler();
		HandlerList list = new HandlerList(duringStartup);
		server.setHandler(list);

		LOG.info("starting the server");
		server.start();
		LOG.info("done starting the server");

		new Thread(() -> deployTheApp(server), "deploy").start();

		server.join();
	}

	private static void deployTheApp(Server server)
	{
		try
		{
			LOG.info("deploying the real application context");

			Resource baseResource = new PathResource(new File("/tmp"));

			ServletContextHandler theRealContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
			theRealContext.setContextPath("/");
			theRealContext.setBaseResource(baseResource);

			theRealContext.addServlet(HelloServlet.class, "/hello");
			theRealContext.addEventListener(new AppInitializer());

			HandlerList list = (HandlerList)server.getHandler();
			Handler duringStartupHandler = list.getHandlers()[0];

			list.addHandler(theRealContext);
			LOG.info("starting the real application context");
			theRealContext.start();

			LOG.info("removing the temporary handler");
			list.removeHandler(duringStartupHandler);

			LOG.info("done deploying the real application context");
		}
		catch (Exception ex)
		{
			LOG.warn("Exception during deferred deploy", ex);
		}
	}
}
