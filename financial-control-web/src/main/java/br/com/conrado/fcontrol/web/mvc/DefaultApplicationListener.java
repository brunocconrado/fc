package br.com.conrado.fcontrol.web.mvc;

import static java.lang.System.err;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import br.com.conrado.fcontrol.commons.util.LogUtils.DiskUsage;
import br.com.conrado.fcontrol.commons.util.LogUtils.SystemPropertiesInitializer;

@Component
@WebListener
@SuppressWarnings("rawtypes")
public class DefaultApplicationListener implements ApplicationListener, HttpSessionListener {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
	LOG.debug("onApplicationEvent({})", event);
	if (event instanceof ApplicationContextEvent) {
	    ApplicationContextEvent applicationContextEvent = (ApplicationContextEvent) event;
	    ApplicationContext applicationContext = applicationContextEvent.getApplicationContext();
	    LOG.warn(applicationContext.getApplicationName() + " " + getStatus((ApplicationContextEvent) event));

	    if (event instanceof ContextStartedEvent || event instanceof ContextRefreshedEvent) {
		logEnvironment(applicationContext);
		LOG.warn("FluxoAV STARTED");
	    }

	    if (event instanceof ContextStoppedEvent || event instanceof ContextClosedEvent) {
		try {
		    ContextStoppedEventHandler.onStop();
		} catch (Exception e) {
		    e.printStackTrace(); // sem contexto de log
		}
	    }

	}
    }

    private static void logEnvironment(ApplicationContext applicationContext) {
	// log das propriedades de sistema
	//WebProperties webProperties = applicationContext.getBean(WebProperties.class);
	DiskUsage.printUsage();
	LOG.info("applicationContext={} webProperties={}", applicationContext, " ");
	SystemPropertiesInitializer.reset(true);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
	LOG.debug("sessionCreated({})", se.getSession() == null ? null : se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
	LOG.debug("sessionDestroyed({})", se.getSession() == null ? null : se.getSession().getId());
    }

    public static void onStop() {
	// LOG finalizado
	err.println("filter stopped");
    }

    protected static String getStatus(ApplicationContextEvent event) {
	try {
	    return event.getClass().getSimpleName().replaceAll("Context|Event", "").toUpperCase();
	} catch (Exception e) {
	    LOG.error("event=" + event, e);
	    return "";
	}
    }

    public static class ContextStoppedEventHandler {

	public static boolean onStop() {
	    ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
	    if (loggerFactory instanceof ch.qos.logback.classic.LoggerContext) {
		// finaliza o contexto async do logback
		err.println("loggerFactory stopped");
		((ch.qos.logback.classic.LoggerContext) loggerFactory).stop();
		return true;
	    }
	    return false;
	}

    }

}