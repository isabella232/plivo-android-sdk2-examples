package com.plivo.endpoint;

import java.util.Map;

public class Global {
	public static boolean DEBUG; // keeping this here to not break the backward compatibility
	static String DOMAIN = BuildConfig.SIP_DOMAIN;

	// keeping this here for backward compatibility
	public static String mapToString(Map<String, String> map) {
		return Utils.mapToString(map);
	}
}