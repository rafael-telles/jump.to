package main.filters;
import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("*")
public class AntiJspFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) req;
		String uri = httpReq.getRequestURI();
		if(!uri.endsWith(".jsp")) {
			String absoluteFilePath = req.getServletContext().getRealPath(uri + ".jsp");
			File file = new File(absoluteFilePath);
	
			if (new File(absoluteFilePath).exists()) { 
			    httpReq.getRequestDispatcher(uri + ".jsp").forward(req, res);
			} else {	
				chain.doFilter(req, res);
			}
		} else {	
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
	
	@Override
	public void destroy() { }

}
