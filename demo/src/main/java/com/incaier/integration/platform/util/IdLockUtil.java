package com.incaier.integration.platform.util;

import java.util.HashMap;

public class IdLockUtil {

    private static HashMap<String, String> mMapId = new HashMap<>();
    private static HashMap<String, String> mMapIdCache = new HashMap<>();

    /**
     * 缓存切换的开始时间，等待{@link #M_CACHE_DELETE_TIME}时间后将清空切换数据
     */
    private static long mCacheCreatTime;

    /**
     * 最大缓存数(当超出这一数值时，会自动清空)，缓存切换等待时间
     */
    private static final int M_MAX_CACHE = 1000;
    private static final int M_CACHE_DELETE_TIME = 10000;

    public static synchronized String getLock(String oldId) {
        String returnSt;
        if (mMapId.size() < M_MAX_CACHE) {
            //数据比较少，普通的返回锁
            if (!mMapId.containsKey(oldId)) {
                mMapId.put(oldId, oldId);
            }
            returnSt = mMapId.get(oldId);
        } else {
            //累加的残留数据太多，切换至缓存

            //缓存开始时间
            long nowMills = System.currentTimeMillis();
            if (mMapIdCache.size() == 0) {
                mCacheCreatTime = nowMills;
            }
            if (!mMapIdCache.containsKey(oldId)) {
                mMapIdCache.put(oldId, mMapId.getOrDefault(oldId, oldId));
            }
            returnSt = mMapIdCache.get(oldId);

            //等待mCacheChangeTime时间后清除原始数据
            if (nowMills - mCacheCreatTime > M_CACHE_DELETE_TIME) {
                mMapId.clear();
                //原始和缓存对调即可实现切换
                HashMap<String, String> change = mMapId;
                mMapId = mMapIdCache;
                mMapIdCache = change;
            }
        }
        return returnSt;
    }
}
