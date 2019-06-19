package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
	@Autowired
	RateLimiterDao rateLimiterDao;
	
	public boolean withinLimit(String clientId, String apiName) {
		boolean result = true;
		int apiQuotaForClient = getQuota(clientId, apiName);
		int currentCount = getCurrentCount(clientId, apiName);
		if (currentCount > apiQuotaForClient)
			return false;
		return result;
	}
	
	public void incrementHitCount(String clientId, String apiName) {
		String key = clientId + RateLimiterConstants.COLON_STRING + apiName;
		String countStr = rateLimiterDao.hget(RateLimiterConstants.COUNT_MAP_NAME, key);
		
		int newCount = 0;
		if (countStr != null)
			newCount = Integer.parseInt(countStr) + 1;
		else
			newCount = 1;
		rateLimiterDao.hset(RateLimiterConstants.COUNT_MAP_NAME, key, String.valueOf(newCount));
	}
	
	private int getQuota(String clientId, String apiName) {
		String key = clientId + RateLimiterConstants.COLON_STRING + apiName;
		return Integer.parseInt(rateLimiterDao.hget(RateLimiterConstants.QUOTA_MAP_NAME, key));
	}
	
	private int getCurrentCount(String clientId, String apiName) {
		String key = clientId + RateLimiterConstants.COLON_STRING + apiName;
		String countStr = rateLimiterDao.hget(RateLimiterConstants.COUNT_MAP_NAME, key);
		int count = (countStr == null) ? 0: Integer.parseInt(countStr);
		return count;
	}
}
