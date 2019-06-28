package com.polygons.queries.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PolygonCacheUtil {

	private static volatile PolygonCacheUtil INSTANCE = null;

	public static PolygonCacheUtil getInstance() {
		if (INSTANCE == null) {
			synchronized (PolygonCacheUtil.class) {
				if (INSTANCE == null) {
					INSTANCE = new PolygonCacheUtil();
				}
			}
		}
		return INSTANCE;
	}

	private Map<String, Object> polygonCache = new ConcurrentHashMap<String, Object>();

	public void putToCache(String polygonId, Object pointList) {
		synchronized (polygonCache) {
			polygonCache.put(polygonId, pointList);
		}

    }

	public Object getFromCache(String polygonId) {
		synchronized (polygonCache) {
			Object pointList = polygonCache.get(polygonId);
			if (pointList == null) {
				return null;
			}
			return pointList;
		}
    }
	
}
