package br.com.conrado.fcontrol.web.config;

import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_COUNTRY;
import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_COUNTRY_UNSET;
import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_ENCODING_DEFAULT;
import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_ENCODING_DEFAULT_UNSET;
import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_LANGUAGE;
import static br.com.conrado.fcontrol.commons.util.FControlEnvironment.APPLICATION_LANGUAGE_UNSET;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.conrado.fcontrol.commons.lang.message.EnvironmentProperties;

//@Configuration
//@ComponentScan("br.com.oi.oicommons.lang")
//@PropertySource("${fluxoav.env.path:classpath:config/environment.properties}") // -Dfluxoav.env.path para caminho absoluto
public class PropertiesConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesConfig.class);

    @Autowired
    private EnvironmentProperties environment;

    @PostConstruct
    public void init() {
        LOG.debug("init() {}", PropertiesConfig.class.getSimpleName());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource bundleMessageSource() throws IOException {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages", "classpath:messages-errors");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding(environment.getProperty(APPLICATION_ENCODING_DEFAULT,
                APPLICATION_ENCODING_DEFAULT_UNSET));
        return messageSource;
    }

    @Bean(name = "locale")
    public Locale locale() {
        return defaultLocale();
    }

    @Bean(name = "defaultLocale")
    public Locale defaultLocale() {
        return new Locale(environment.getProperty(APPLICATION_LANGUAGE, APPLICATION_LANGUAGE_UNSET),
                environment.getProperty(APPLICATION_COUNTRY, APPLICATION_COUNTRY_UNSET));
    }

    @Bean
    public FixedLocaleResolver localeResolver() {
        return new FixedLocaleResolver(defaultLocale());
    }

    @PreDestroy
    public void destroy() {
        LOG.debug("stop() {}", getClass().getSimpleName());
    }

}
