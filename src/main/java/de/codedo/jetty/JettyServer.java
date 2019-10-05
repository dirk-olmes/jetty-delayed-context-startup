package de.codedo.jetty;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;

public class JettyServer
{
	public static void main(String[] args) throws Exception
	{
		Server server = new Server(8181);

		Resource baseResource = new PathResource(new File("/tmp"));

		ServletContextHandler theRealContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		theRealContext.setContextPath("/");
		theRealContext.setBaseResource(baseResource);
		// server.setHandler(theRealContext);

		theRealContext.addServlet(HelloServlet.class, "/hello");
		theRealContext.addEventListener(new AppInitializer());

		ContextHandler duringStartup = new DuringStartupHandler();
		HandlerList handlerList = new HandlerList(duringStartup, theRealContext);
		server.setHandler(handlerList);

		server.start();
		server.join();
	}
}
