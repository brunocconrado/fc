package br.com.conrado.fcontrol.domain.factory;

public interface EntityFactory {
    
    public void init();
    
    public <I, T> I getNewInstance(Class<T> clazz) throws ClassNotFoundException;

}
