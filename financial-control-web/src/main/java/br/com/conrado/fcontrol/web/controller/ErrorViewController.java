package br.com.conrado.fcontrol.web.controller;

import static br.com.conrado.fcontrol.web.mvc.interceptor.DefaultExceptionHandler.logHeaders;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ErrorViewController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorViewController.class);

    public static final String JSON_ERROR_VIEW = "/errorjs";
    public static final String ERROR_VIEW = "/html/error";
    public static final String FALLBACK_ERROR_VIEW = "/error";

    public static final String SESSION_TIMED_OUT_PARAM = "sessionexp";
    public static final String SESSION_TIMED_OUT = "/?" + SESSION_TIMED_OUT_PARAM + "=1";

    @Override
    protected String currentView() {
        return ERROR_VIEW;
    }

    @Override
    @RequestMapping(value = ERROR_VIEW, method = { RequestMethod.GET, RequestMethod.POST })
    public String initView(Model model, HttpServletRequest request, @RequestHeader Map<String, String> headers,
            HttpServletResponse response) {
        return logHeaders(request, response, headers, ERROR_VIEW);
    }

    @RequestMapping(value = FALLBACK_ERROR_VIEW, method = { RequestMethod.GET, RequestMethod.POST })
    public String initFallbackView(Model model, HttpServletRequest request, @RequestHeader Map<String, String> headers,
            HttpServletResponse response) {
        return logHeaders(request, response, headers, FALLBACK_ERROR_VIEW);
    }

    @RequestMapping(value = ERROR_VIEW, params = "sid", method = RequestMethod.GET)
    public String verifySessionStatus(WebRequest webRequest, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        LOG.debug("description={}, isNew={}", webRequest.getDescription(true), session == null || session.isNew());
        return logHeaders(request, response, null, ERROR_VIEW);
    }

}
