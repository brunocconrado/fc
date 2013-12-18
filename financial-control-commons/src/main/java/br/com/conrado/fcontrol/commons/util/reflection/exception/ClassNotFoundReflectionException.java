package br.com.conrado.fcontrol.commons.util.reflection.exception;

public class ClassNotFoundReflectionException extends ReflectionException {

    private static final long serialVersionUID = 1738936877982399345L;

    public ClassNotFoundReflectionException() {
	super();
    }

    public ClassNotFoundReflectionException(String message, Throwable cause) {
	super(message, cause);
    }

    public ClassNotFoundReflectionException(String message) {
	super(message);
    }

    public ClassNotFoundReflectionException(Throwable cause) {
	super(cause);
    }

}
