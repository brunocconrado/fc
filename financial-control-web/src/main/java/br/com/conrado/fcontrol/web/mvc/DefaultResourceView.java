package br.com.conrado.fcontrol.web.mvc;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.InternalResourceView;

import br.com.conrado.fcontrol.web.mvc.interceptor.DefaultExceptionHandler;

/**
 * View que redireciona o tratamento de erros de rendering ao DefaultExceptionHandler
 */
public class DefaultResourceView extends InternalResourceView {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultResourceView.class);

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            if (LOG.isDebugEnabled()) {
                LOG.debug("rendering " + getStaticAttributes());
            }

            super.renderMergedOutputModel(model, request, response);
        } catch (Exception ex) {
            DefaultExceptionHandler.handle(request, response, ex);
            // suprime o stacktrace original do stdout para manter erros centralizados no log interno do DefaultExceptionHandler
            throw new ServletException(ex.getMessage());
        }
    }

}
