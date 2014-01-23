package br.com.conrado.fcontrol.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.conrado.fcontrol.web.enumeration.PageView;

@Controller
public class UnauthorizedController extends BaseController {

    @Override
    protected String currentView() {
	return PageView.UNAUTHORIZED_VIEW.getPageView();
    }

    @Override
    @RequestMapping(value = "/api/unauthorized", method = RequestMethod.GET)
    protected String initView(Model model, HttpServletRequest request, Map<String, String> headers,
	    HttpServletResponse response) throws Exception {
	return currentView();
    }

}
