package br.com.conrado.fcontrol.commons.util.reflection;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import br.com.conrado.fcontrol.commons.util.reflection.exception.ClassNotFoundReflectionException;
import br.com.conrado.fcontrol.commons.util.reflection.exception.ReflectionException;

@SuppressWarnings("rawtypes")
public class ReflectionsUtil {

    public static <T> T newInstance(String className, Class<T> type) throws ClassNotFoundReflectionException {

	try {
	    return type.cast(newInstance(Class.forName(className)));
	} catch (ClassNotFoundException e) {
	    throw new ClassNotFoundReflectionException("Class " + className + " has not found");
	}
    }

    public static <T> T newInstance(Class<T> type) throws ReflectionException {
	try {
	    return type.newInstance();
	} catch (InstantiationException e) {
	    throw new ReflectionException(e);
	} catch (IllegalAccessException e) {
	    throw new ReflectionException(e);
	}
    }

    public static Set<Class> getPackageClasses(Package classPackage) {

	Set<Class> classes = new HashSet<Class>();
	try {
	    ClassLoader cl = classPackage.getClass().getClassLoader();
	    if (cl == null) {
		cl = Thread.currentThread().getContextClassLoader();
	    }
	    String packageUrl = classPackage.getName().replaceAll("\\.", "/");
	    Enumeration<URL> packageLocations = cl.getResources(packageUrl);

	    while (packageLocations.hasMoreElements()) {
		URL url = packageLocations.nextElement();
		process(classes, url, classPackage.getName());
	    }

	    return classes;
	} catch (IOException e) {
	    throw new ReflectionException(e);
	}
    }

    private static void process(Set<Class> classes, URL url, String base) throws IOException {
	try {

	    if (url.toString().contains("jar:")) {
		String urlString = url.toString();
		urlString = urlString.substring(urlString.indexOf(":") + 1);
		url = new URL(urlString);
	    }

	    File folder = new File(url.toURI());

	    File[] files = folder.listFiles(new FilenameFilter() {
		@Override
		public boolean accept(File file, String name) {
		    return name.indexOf("$") < 0 && name.endsWith(".class");
		}
	    });

	    if (files != null) {
		for (File f : files) {
		    String name = base + "." + f.getName().substring(0, f.getName().indexOf('.'));
		    classes.add(loadClass(name));
		}
	    }
	} catch (URISyntaxException e) {
	    throw new IOException(e);
	}
    }

    private static Class<?> loadClass(String className) throws ClassNotFoundReflectionException {
	try {
	    return Class.forName(className);
	} catch (ClassNotFoundException e) {
	    throw new ClassNotFoundReflectionException("Class " + className + " has not found");
	}
    }
}
