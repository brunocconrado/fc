package br.com.conrado.fcontrol.commons.util.reflection.exception;

public class ReflectionException extends RuntimeException {

    private static final long serialVersionUID = 1883307231768669392L;

    public ReflectionException() {
	super();
    }

    public ReflectionException(String message, Throwable cause) {
	super(message, cause);
    }

    public ReflectionException(String message) {
	super(message);
    }

    public ReflectionException(Throwable cause) {
	super(cause);
    }

}
