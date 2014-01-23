package br.com.conrado.fcontrol.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.conrado.fcontrol.web.UserControl;

public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {

	UserControl userControl = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext())
		.getBean(UserControl.class);
	
	if(userControl.restore() == null) {
	    
	}
	
	filterChain.doFilter(request, response);
	
    }

}
