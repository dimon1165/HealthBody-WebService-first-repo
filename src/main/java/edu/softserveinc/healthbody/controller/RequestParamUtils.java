package edu.softserveinc.healthbody.controller;

public class RequestParamUtils {

	
	public static Object toObject(Class<?> clazz, String value) {
		if (Boolean.class == clazz)
			return Boolean.parseBoolean(value);
		if (Byte.class == clazz)
			return Byte.parseByte(value);
		if (Short.class == clazz)
			return Short.parseShort(value);
		if (Integer.class == clazz || int.class == clazz)
			return Integer.parseInt(value);
		if (Long.class == clazz)
			return Long.parseLong(value);
		if (Float.class == clazz)
			return Float.parseFloat(value);
		if (Double.class == clazz)
			return Double.parseDouble(value);
		return value;
	}

	public static boolean isEmptyParam(String param) {
		if (param == null || param.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isIntegerParam(String param) {

		return param.matches("^-?\\d+$");

	}

	public static int getIntegerParam(String param) {
		if (isIntegerParam(param)) {
			return Integer.valueOf(param);
		} else {
			throw new IllegalArgumentException("Error while parsing integer, check out 'partSize' or 'partNumber'");
		}
	}
}
