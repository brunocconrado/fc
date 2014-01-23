package br.com.conrado.fcontrol.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.factory.EntityFactory;
import br.com.conrado.fcontrol.service.UserService;
import br.com.conrado.fcontrol.web.enumeration.PageView;

public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    
    @Autowired
    private EntityFactory entityFactory;
    
    @Autowired
    private UserService userService;

    @Override
    protected String currentView() {
	return PageView.USER_REGISTER.getPageView();
    }

    @Override
    @RequestMapping(value = "/api/user/register", method = RequestMethod.GET)
    protected String initView(Model model, HttpServletRequest request, Map<String, String> headers,
	    HttpServletResponse response) throws Exception {

	model.addAttribute("user", entityFactory.getNewInstance(User.class));

	return currentView();
    }

    @RequestMapping(value = "/api/user/register", method = RequestMethod.GET)
    protected String register(@ModelAttribute("user")@Valid User user, BindingResult bindingResult, Model model,
	    HttpServletRequest request, Map<String, String> headers, HttpServletResponse response) throws Exception {
	
	if(bindingResult.hasErrors()) {
	    LOG.debug("User validation error: {}", user);
	}
	
	userService.create(user);
	
	return currentView();
    }

}
