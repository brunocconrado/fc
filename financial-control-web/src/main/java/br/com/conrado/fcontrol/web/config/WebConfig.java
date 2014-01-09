package br.com.conrado.fcontrol.web.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.extras.conditionalcomments.dialect.ConditionalCommentsDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import br.com.conrado.fcontrol.web.mvc.DefaultResourceView;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.conrado.fcontrol")
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    public static final String VIEW_PREFIX = "/WEB-INF/pages/";
    public static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public static final int YEAR_IN_SECONDS = 31556926;

    @PostConstruct
    public void init() {
	LOG.debug("init() {} (debug={})", WebConfig.class.getSimpleName(), true);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	configurer.enable();
    }

   // @Bean
    public ViewResolver getJspViewResolver() {
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setContentType(CONTENT_TYPE);
	resolver.setPrefix(VIEW_PREFIX);
	resolver.setSuffix(".jsp");
	resolver.setViewClass(DefaultResourceView.class);
	resolver.setOrder(2);
	return resolver;
    }

    @Bean
    public ViewResolver getHtmlViewResolver() {
	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
	templateResolver.setTemplateMode("LEGACYHTML5");
	templateResolver.setPrefix(VIEW_PREFIX);
	templateResolver.setSuffix(".html");
	templateResolver.setCharacterEncoding("UTF-8");
	templateResolver.setOrder(1);

	SpringTemplateEngine engine = new SpringTemplateEngine();
	engine.setTemplateResolver(templateResolver);
	engine.addDialect(new ConditionalCommentsDialect()); // suporte ao IE

	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	viewResolver.setViewNames(new String[] { "/private/*", "/public/*" }); // para ignorar os jsps
	viewResolver.setContentType(CONTENT_TYPE);
	viewResolver.setTemplateEngine(engine);
	if (true) {
	    viewResolver.setCache(false);
	}
	viewResolver.setOrder(1);
	return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	Integer cacheInSeconds = true ? 0 : YEAR_IN_SECONDS;
	registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(cacheInSeconds);
	registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(cacheInSeconds);
	registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(cacheInSeconds);
    }

    @Bean
    public ObjectMapper objectMapper() {
	// serializar via field ao inves de getters/setters (JSON)
	ObjectMapper mapper = new ObjectMapper();
	mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
	mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	return mapper;
    }

    @PreDestroy
    public void destroy() {
	LOG.debug("stop() {}", getClass().getSimpleName());
    }

}
