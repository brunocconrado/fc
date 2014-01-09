package br.com.conrado.fcontrol.commons.lang.message;

import org.springframework.core.env.Environment;

public interface EnvironmentProperties extends Environment {

    /**
     * @return O valor da propriedade. Se não houver configuração, devolve o valor de "-Dcustom, ou "" ao invés de nulo.
     */
    @Override
    String getProperty(String key);

    @Override
    String getProperty(String key, String defaultValue);

    /**
     * @return O valor da propriedade. Se não houver configuração, devolve o valor de "-Dcustom, ou 0 ao invés de nulo.
     *         Devolve nulo se não for possível converter valor em int.
     */
    Integer getIntProperty(String key);

    Integer getIntProperty(String key, Integer defaultValue);

}