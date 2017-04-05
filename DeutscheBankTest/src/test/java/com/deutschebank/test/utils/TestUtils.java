package com.deutschebank.test.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class TestUtils {
	public String getFile(String fileName) {
		String result = "";
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
}
