package com.boaglio.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.boaglio.validation.UserInfo;

/*
 * Local cache manager ...instead of HashMap you should use Redis , Hazelcast , etc...
 */
public class CacheManager {

	private static HashMap<String,UserInfo> cache = new HashMap<String,UserInfo>();

	public static boolean hasUser(String login) {
		boolean hasUser = false;
		UserInfo u = cache.get(login);
		if (u != null) {
			hasUser = true;
		}
		return hasUser;
	}

	public static boolean hasUserUUID(String login,String uuid) {
		boolean hasUser = false;
		UserInfo u = cache.get(login);
		if (u != null && u.getUuid() != null && u.getUuid().equalsIgnoreCase(uuid)) {
			hasUser = true;
		}
		return hasUser;
	}

	public static void clearCache() {
		cache = new HashMap<String,UserInfo>();
	}

	public static boolean updateUserUUID(String login,String uuid) {
		boolean hasUser = false;
		UserInfo u = cache.get(login);
		if (u == null) {
			u = new UserInfo();
			u.setLogin(login);
		}
		u.setUuid(uuid);
		u.setDtLastUpdate(new Date());

		cache.put(login,u);

		System.out.println("Adding " + u);
		return hasUser;
	}

	public static boolean removeUserUUID(String login) {
		boolean removed = false;

		if (login != null && cache.containsKey(login)) {
			cache.remove(login);
			System.out.println("Removing " + login);
			removed = true;
		}

		System.out.println("cache size: " + cache.size());
		
		return removed;

	}

	public static List<UserInfo> getList() {

		List<UserInfo> list = new ArrayList<UserInfo>();
		for (String login : cache.keySet()) {
			list.add(cache.get(login));
		}

		System.out.println("cache size: " + cache.size());

		return list;
	}

	public static UserInfo getUserInfoByLogin(String login) {
		return cache.get(login);
	}

}
