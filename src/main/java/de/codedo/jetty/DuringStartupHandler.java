package de.codedo.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;

public class DuringStartupHandler extends ContextHandler
{
	@Override
	public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		baseRequest.setHandled(true);
	}
}
