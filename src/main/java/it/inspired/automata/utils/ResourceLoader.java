package it.inspired.automata.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class ResourceLoader {
	
	public URL getResource(String resource) {
		resource = resource.trim();

		URL result = Thread.currentThread().getContextClassLoader()
				.getResource(resource);

		// Could not find resource. Try with the classloader that loaded this
		// class.
		if (result == null) {
			ClassLoader classLoader = ResourceLoader.class.getClassLoader();
			if (classLoader != null) {
				result = classLoader.getResource(resource);
			}
		}

		// Last ditch attempt searching classpath
		if (result == null) {
			result = ClassLoader.getSystemResource(resource);
		}

		// one more time
		if (result == null && StringUtils.contains(resource, ":")) {
			try {
				result = new URL(resource);
			} catch (MalformedURLException e) {
				throw new RuntimeException( e );
			}
		}

		return result;
	}
}
