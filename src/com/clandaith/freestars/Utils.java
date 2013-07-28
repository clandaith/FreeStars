package com.clandaith.freestars;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 28, 2013 9:23:17 AM
 *
 */

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	public static String convertObjectToString(Object o) {
		String objectString = "+++++++++++++++++++\n" + o.getClass().getSimpleName() + "\n";

		Method[] methods = o.getClass().getMethods();
		for (Method method : methods) {
			try {
				if (method.getName().equals("getClass")) {
					continue;
				}

				if (method.getName().startsWith("get")) {
					objectString += "\t" + method.getName() + ": " + method.invoke(o, null) + "\n";
				}
				if (method.getName().startsWith("is")) {
					objectString += "\t" + method.getName() + ": " + method.invoke(o, null) + "\n";
				}
				if (method.getName().startsWith("has")) {
					objectString += "\t" + method.getName() + ": " + method.invoke(o, null) + "\n";
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("Error: ", e);
			}
		}

		return objectString;
	}
}
