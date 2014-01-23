package br.com.conrado.fcontrol.web.mvc.interceptor;

import static br.com.conrado.fcontrol.web.controller.ErrorViewController.JSON_ERROR_VIEW;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import br.com.conrado.fcontrol.web.controller.ErrorViewController;
import br.com.conrado.fcontrol.web.util.RequestUtils;

@ControllerAdvice
public class DefaultExceptionHandler extends DefaultHandlerExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    public static final String APPLICATION_JSON = "application/json";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        handle(request, response, ex);

        if (request.getContentType() != null && APPLICATION_JSON.equals(request.getContentType())) {
            LOG.debug("{}", request.getContentType());
            request.setAttribute("javax.servlet.error.exception", ex); // renderiza o erro em formato JSON
            return new ModelAndView(JSON_ERROR_VIEW);
        }
        return new ModelAndView(); // error page do web.xml
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // TODO o DefaultHandlerExceptionResolver sobrescreve o ResponseStatusExceptionResolver
    public String sessionExpired(HttpServletRequest request, HttpServletResponse response,
            HttpSessionRequiredException ex) throws IOException {
        String view = logHeaders(request, response, RequestUtils.pathInfo(request), ErrorViewController.FALLBACK_ERROR_VIEW);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return view;
    }

    public static void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        String servletPath = request == null ? null : request.getServletPath();
        LOG.error("Unexpected error at {}", servletPath, exceptionToLog(ex));

        try {
            // igual ao super.sendServerError() suprimindo o stack
            response.sendError(errorCode(ex));
        } catch (Exception handlerException) {
            LOG.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }

    }

    public static String logHeaders(HttpServletRequest request, HttpServletResponse response,
            Object headers, String currentView) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String referer = request.getHeader("referer");
        if (LOG.isDebugEnabled()) {
            LOG.debug("errorLog(headers={}, currentView={}, statusCode={}, requestUri={}, referer={})", headers, currentView, statusCode, requestUri, referer, throwable);
        }
        return currentView;
    }

    private static int errorCode(Exception ex) {
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            // erro 400
            return HttpServletResponse.SC_BAD_REQUEST;
        }
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    private static Exception exceptionToLog(Exception ex) {
        if (LOG.isTraceEnabled()) {
            return ex;
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            // suprime erros de protocolo incorreto
            return null;
        }
        return ex;
    }

}
