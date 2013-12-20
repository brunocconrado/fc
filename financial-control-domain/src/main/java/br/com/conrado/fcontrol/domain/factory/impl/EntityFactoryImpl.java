package br.com.conrado.fcontrol.domain.factory.impl;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.conrado.fcontrol.commons.util.reflection.ReflectionsUtil;
import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.factory.EntityFactory;

//TODO Melhor lugar para colocar esta classe. Onde?

public class EntityFactoryImpl implements EntityFactory {
    
    private static final Logger LOG = LoggerFactory.getLogger(EntityFactoryImpl.class);
    
    private Map<String, Class<?>> domainClasses = new TreeMap<String, Class<?>>();
    
    private Package baseImplementationPackage;

    public EntityFactoryImpl(Package baseImplementationPackage) {
	this.baseImplementationPackage = baseImplementationPackage;
    }

    @PostConstruct
    @SuppressWarnings("rawtypes")
    public void init() {

	Set<Class> classes = ReflectionsUtil.getPackageClasses(baseImplementationPackage);
	for (Class clazz : classes) {
	    for (Class interfaces : clazz.getInterfaces()) {
		if (User.class.getPackage().equals(interfaces.getPackage())) {
		    domainClasses.put(interfaces.getCanonicalName(), clazz);
		    break;
		}
	    }
	}
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <I, T> I getNewInstance(Class<T> clazz) throws ClassNotFoundException {
	Class<?> instanceClass = domainClasses.get(clazz.getCanonicalName());
	if(instanceClass == null) {
	    LOG.warn("Class not found for {}", clazz);
	    throw new ClassNotFoundException();
	}
	LOG.debug("Creating new class for {}-{}", clazz.getCanonicalName(), instanceClass.getCanonicalName());
	return (I) ReflectionsUtil.newInstance(instanceClass);
    }
    
    @PreDestroy
    public void destroy() {
        LOG.debug("stop() {}", getClass().getSimpleName());
    }

}
