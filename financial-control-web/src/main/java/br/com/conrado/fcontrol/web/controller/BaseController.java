package br.com.conrado.fcontrol.web.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Adiciona logs e tratamentos de erros comuns nas chamadas do controller
 */
public abstract class BaseController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {

    }

    public String rejectException(BindingResult bindingResult, Throwable e) {
	return rejectException(bindingResult, null, e);
    }

    public String reject(BindingResult bindingResult, Model model, String messageCode, Object... args) {
	return reject(currentView(), bindingResult, model, messageCode, args);
    }

    public String reject(String view, BindingResult bindingResult, Model model, String messageCode, Object... args) {
	LOG.warn("reject: {} -> {}", messageCode, view);
	bindingResult.reject(messageCode, args, messageCode);
	return redirectWithRejectedResult(bindingResult, model, view);
    }

    public String rejectException(BindingResult bindingResult, Model model, Throwable e) {
	LOG.warn("Submit with exception: {}", bindingResult, e);
	/*
	 * String errorMessage = defaultErrorMessage; if (e instanceof LocalizedException) { errorMessage =
	 * ((LocalizedException) e).getLocalizedMessage(); bindingResult.reject(errorMessage, ((LocalizedException)
	 * e).getLocalizedArgs(), defaultErrorMessage); } else if(e instanceof UnavailableException) { throw
	 * (UnavailableException) e; } else { bindingResult.reject(GENERIC_ERROR, null, defaultErrorMessage); }
	 */
	return redirectWithRejectedResult(bindingResult, model, errorView("Error Message"));
    }

    private String redirectWithRejectedResult(BindingResult bindingResultWithErrors, Model model, String returnView) {
	LOG.debug("errorView={}", returnView);
	return returnView;
    }

    /**
     * return A view (html/thymeleaf, jsp ou json)
     */
    protected abstract String currentView();

    protected String errorView(String errorMessage) {
	/*
	 * if (LOG.isDebugEnabled()) { LOG.debug("errorMessage={} -> {}", errorMessage,
	 * messages.getMessage(errorMessage)); }
	 */
	return currentView();
    }

    /**
     * @return Renderiza os componentes da view
     */
    // "abstract": cada impl. deve incluir o @RequestMapping por view
    protected abstract String initView(Model model, HttpServletRequest request, Map<String, String> headers,
	    HttpServletResponse response) throws Exception;

}
