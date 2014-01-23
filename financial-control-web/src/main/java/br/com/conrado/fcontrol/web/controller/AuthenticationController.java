package br.com.conrado.fcontrol.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.conrado.fcontrol.domain.Credentials;
import br.com.conrado.fcontrol.service.AuthenticationService;
import br.com.conrado.fcontrol.web.annotation.Private;
import br.com.conrado.fcontrol.web.enumeration.PageView;

@Controller
public class AuthenticationController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private static final String MODEL_NAME = "credentials";

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected String currentView() {
	return PageView.AUTHENTICATION.getPageView();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initView(Model model, HttpServletRequest request, Map<String, String> headers,
	    HttpServletResponse response) throws Exception {

	if (LOG.isDebugEnabled()) {
	    LOG.debug("new request in {}", this.getClass().getCanonicalName());
	}

	model.addAttribute(MODEL_NAME, new Credentials());

	return currentView();
    }

    @Private
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(MODEL_NAME) @Valid Credentials credentials, BindingResult bindingResult,
	    Model model, RedirectAttributes redirectAttributes) {

	if (bindingResult.hasErrors()) {
	    LOG.debug("Submit binding errors: {}", bindingResult);
	    return currentView();
	}

	authenticationService.authenticate(credentials);
	
	return PageView.MAIN.getPageView();
    }

}
