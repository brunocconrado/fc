package br.com.conrado.fcontrol.commons.lang.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.conrado.fcontrol.commons.lang.message.EnvironmentProperties;

@Component
public class EnvironmentPropertiesImpl implements EnvironmentProperties {

    @Autowired
    private Environment environment;

    @Override
    public boolean containsProperty(String key) {
        return environment.containsProperty(key);
    }

    @Override
    public String getProperty(String key) {
        return getProperty(key, "");
    }

    @Override
    public Integer getIntProperty(String key) {
        return getIntProperty(key, 0);
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) {
        try {
            return environment.getProperty(key, Integer.class, defaultValue == null ? null :
                    Integer.valueOf(System.getProperty(key, String.valueOf(defaultValue))));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        try {
            return environment.getProperty(key,
                    defaultValue == null ? null : System.getProperty(key, defaultValue));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return environment.getProperty(key, targetType);
    }

    @Override
    public String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }

    @Override
    public String[] getDefaultProfiles() {
        return environment.getDefaultProfiles();
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return environment.getProperty(key, targetType, defaultValue);
    }

    @Override
    public <T> Class<T> getPropertyAsClass(String key, Class<T> targetType) {
        return environment.getPropertyAsClass(key, targetType);
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return environment.getRequiredProperty(key);
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return environment.getRequiredProperty(key, targetType);
    }

    @Override
    public String resolvePlaceholders(String text) {
        return environment.resolvePlaceholders(text);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return environment.resolveRequiredPlaceholders(text);
    }

    @Override
    public boolean acceptsProfiles(String... profiles) {
        return environment.acceptsProfiles(profiles);
    }

    @Override
    public String toString() {
        /* gerar pelo eclipse */
        return "EnvironmentPropertiesImpl [environment=" + environment + "]";
    }

}
