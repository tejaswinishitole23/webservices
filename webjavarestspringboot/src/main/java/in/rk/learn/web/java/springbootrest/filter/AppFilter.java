package in.rk.learn.web.java.springbootrest.filter;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class AppFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (httpServletRequest.getContentType() == MediaType.APPLICATION_JSON.toString()
				|| httpServletRequest.getContentType() == MediaType.APPLICATION_XML.toString()) {
			CachedBodyHttpServletRequest servletRequest = new CachedBodyHttpServletRequest(httpServletRequest);
			CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(
					servletRequest);
			ServletInputStream inputStream = cachedBodyHttpServletRequest.getInputStream();
			InputStreamReader isReader = new InputStreamReader(inputStream);
			char charArray[] = new char[cachedBodyHttpServletRequest.getContentLength()];
			isReader.read(charArray);
			String msg = new String(charArray);
			System.out.println("msg:"+msg);
			filterchain.doFilter(cachedBodyHttpServletRequest, response);
			request = cachedBodyHttpServletRequest;
		} else {
			filterchain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {

	}
}