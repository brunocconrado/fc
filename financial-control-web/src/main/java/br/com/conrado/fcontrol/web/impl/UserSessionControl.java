package br.com.conrado.fcontrol.web.impl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.service.UserService;
import br.com.conrado.fcontrol.web.UserControl;

public class UserSessionControl implements UserControl {

    private static final Logger LOG = LoggerFactory.getLogger(UserSessionControl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
	LOG.debug("Init {}, Request {}", this.getClass().getCanonicalName(), request.getClass().getCanonicalName());
    }

    @Override
    public void store(User user) {
	LOG.debug("Store user in session. {}", request.getClass().getCanonicalName());
    }

    @Override
    public User restore() {
	return userService.findUserById((String) request.getSession().getAttribute(USER_ID));
    }

}
