package br.com.conrado.fcontrol.web.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.web.UserControl;
import br.com.conrado.fcontrol.web.annotation.Private;
import br.com.conrado.fcontrol.web.enumeration.PageView;
import br.com.conrado.fcontrol.web.util.RequestUtils;

@Component
public class SecurityHandleInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityHandleInterceptor.class);

    @Autowired
    private UserControl userControl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	boolean canAccess = true;
	if (handler instanceof HandlerMethod) {
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    LOG.debug("preHandle call on bean {} method {}", handlerMethod.getBean().getClass().getCanonicalName(),
		    handlerMethod.getMethod().getName());

	    User user = userControl.restore();

	    canAccess = !(user == null && handlerMethod.getMethod().isAnnotationPresent(Private.class));
	    
	    if(!canAccess) {
		response.sendRedirect(request.getContextPath() +  PageView.UNAUTHORIZED_INIT_VIEW.getPageView());
		//RequestUtils.redirectUrl(request, response, PageView.MAIN.getPageView());
	    }
	}

	return canAccess;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {

	if (handler instanceof HandlerMethod) {
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    LOG.debug("postHandle: call on bean {} method {}. redirect to {}", handlerMethod.getBean().getClass()
		    .getCanonicalName(), handlerMethod.getMethod().getName(), modelAndView.getViewName());
	}
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
	    throws Exception {
	if (handler instanceof HandlerMethod) {
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    LOG.debug("afterCompletion: finished call on bean {} method {} with exception {}", handlerMethod.getBean()
		    .getClass().getCanonicalName(), handlerMethod.getMethod().getName(), e);
	}
    }

}
