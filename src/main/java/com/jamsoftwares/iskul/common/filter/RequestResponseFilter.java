package com.jamsoftwares.iskul.common.filter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(2)
public class RequestResponseFilter implements Filter {

	private Logger logger = LogManager.getLogger(RequestResponseFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		logger.info("{}:\"{}\" Request", req.getMethod(), req.getRequestURI());

		chain.doFilter(request, response);

		logger.info("{}:\"{}\" Response", req.getMethod(), req.getRequestURI(), res.getContentType());
	}
}
