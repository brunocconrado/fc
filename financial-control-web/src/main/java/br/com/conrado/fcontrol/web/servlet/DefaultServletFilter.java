package br.com.conrado.fcontrol.web.servlet;

import static br.com.conrado.fcontrol.web.servlet.RequestUtils.pathInfo;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.HtmlUtils;

import br.com.conrado.fcontrol.web.mvc.DefaultApplicationListener;

public class DefaultServletFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultServletFilter.class);

    private String environmentProfile;

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        if (environment != null) {
           // this.environmentProfile = environment.getProperty(FLUXOAV_ENV, "");
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("filter(sid={}, uri={}, referer={})", request.getRequestedSessionId(), pathInfo(request), request.getHeader("referer"));
            }
            chain.doFilter(wrap(request), response);
        } catch (Exception ex) {
            handleException(request, ex);
        }
    }

    /**
     * @see br.com.oi.fluxoav.web.mvc.interceptor.DefaultExceptionHandler
     */
    private void handleException(HttpServletRequest request, Exception ex) throws ServletException {
        // toda exception deveria ser interceptada pelo DefaultExceptionHandler
        LOG.error(getMessage(ex.getCause()) + ": " + request.getServletPath(), ex);
        throw new ServletException(ex.getMessage(), ex.getCause());
    }

    private String getMessage(Throwable exCause) {
        return "Unexpected error " + (exCause == null ? null : exCause.getMessage());
    }

    public HttpServletRequest wrap(HttpServletRequest request) {
        return new XSSRequestWrapper(true, request);
    }

    @Override
    public void destroy() {
        super.destroy();
        DefaultApplicationListener.onStop();
    }

}

/**
 * @see <a
 *      href="http://ricardozuasti.com/2012/stronger-anti-cross-site-scripting-xss-filter-for-java-web-apps/">source</a>
 */
class XSSRequestWrapper extends HttpServletRequestWrapper {

    private static final String NULL_CHAR = "\0";

    private static final String REPLACEMENT = "[_xss_]";

    private static Pattern[] patterns = new Pattern[] {
            // Script fragments
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // src='...'
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL),
            // lonely script tags
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // eval(...)
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // expression(...)
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // javascript:...
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // vbscript:...
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            // onload(...)=...
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL) };

    private final boolean allowRequest;

    public XSSRequestWrapper(boolean allowRequest, HttpServletRequest servletRequest) {
        super(servletRequest);
        this.allowRequest = allowRequest;
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        return stripXSS(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return stripXSS(super.getHeader(name));
    }

    private String stripXSS(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String parsedValue = null;
        // NOTE: It's highly recommended to use the ESAPI library and
        // uncomment the following line to
        // avoid encoded attacks.
        // value = ESAPI.encoder().canonicalize(value);

        // Avoid null characters
        parsedValue = value.replaceAll(NULL_CHAR, REPLACEMENT);

        // Remove all sections that match a pattern
        for (Pattern scriptPattern : patterns) {
            parsedValue = scriptPattern.matcher(parsedValue).replaceAll(REPLACEMENT);
        }

        if (!allowRequest && parsedValue.contains(REPLACEMENT)) {
            throw new IllegalArgumentException(value);
        }
        return HtmlUtils.htmlEscape(parsedValue);
    }
}
