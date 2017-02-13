package com.boaglio.util;

import javax.servlet.http.HttpServletRequest;

public class NetUtil {

	private static final String SERVER1 = "localhost";
	private static final String SERVER2 = "127.0.0.1";

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getHostname(HttpServletRequest request) {
		if (request != null) {
			return request.getRemoteHost();
		} else {
			return "";
		}
	}

	public static String getURI(HttpServletRequest request) {
		if (request != null) {
			return request.getPathInfo();
		} else {
			return "";
		}
	}

	public static boolean isIgnoreListServer(HttpServletRequest request) {
		boolean resultado = false;
		if (getHostname(request).equalsIgnoreCase(SERVER1) || getHostname(request).equalsIgnoreCase(SERVER2)  ) {
			resultado = true;
		}
		return resultado;
	}
 

}
