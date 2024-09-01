package com.citadini.ourcity.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeaderExposureFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		res.addHeader("access-control-expose-headers", "location");
		filterChain.doFilter(servletRequest, res);
	}

	@Override
	public void destroy() {
	}

}
