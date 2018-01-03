package com.tcl.xpay.redis.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.tcl.xpay.redis.dao.RedisDao;
import com.tcl.xpay.redis.service.ICacheService;
import com.tcl.xpay.utils.JSONUtils;

/**
 * <br>
 * <b>功能：</b>redis基础服务类<br>
 */
@Service("redisCacheService")
public class RedisCacheService implements ICacheService {

	@Autowired
	private RedisDao redisDao;

	/********* (1)、Key（键） ****************************************/
	@Override
	public Boolean exists(String key) {
		return redisDao.exists(key);
	}

	@Override
	public void delete(String key) {
		redisDao.delete(key);
	}

	@Override
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisDao.expire(key, timeout, unit);
	}

	@Override
	public Long getExpire(String key) {
		return redisDao.getExpire(key);
	}

	@Override
	public Long getExpire(String key, TimeUnit unit) {
		return redisDao.getExpire(key, unit);
	}

	@Override
	public void rename(String key, String newKey) {
		redisDao.rename(key, newKey);
	}

	@Override
	public String type(String key) {
		return redisDao.type(key);
	}

	/**
	 * 模糊查询key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> findKeys(String pattern) {
		if (Strings.isNullOrEmpty(pattern)) {
			return null;
		}

		return redisDao.findKeys(pattern);
	}

	/********* (2)、String（字符串） ****************************************/
	@Override
	public long increment(String key, long offset) {
		return redisDao.increment(key, offset);
	}

	/*-------------valueOps put ------------*/
	@Override
	public void put(String key, Object value) {
		redisDao.put(key, JSONUtils.objToJson(value));
	}

	@Override
	public void put(String key, Object value, long timeout, TimeUnit unit) {
		if (timeout > 0) {
			redisDao.put(key, JSONUtils.objToJson(value), timeout, unit);
		} else {
			redisDao.put(key, JSONUtils.objToJson(value));
		}
	}

	@Override
	public void putKeepTTL(String key, Object value) {

		redisDao.putKeepTTL(key, JSONUtils.objToJson(value));
	}

	@Override
	public void putMulti(Map<String, Object> keyValue) {
		if (keyValue.isEmpty()) {
			return;
		}
		final Map<String, String> data = new LinkedHashMap<String, String>(keyValue.size());
		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			data.put(entry.getKey(), JSONUtils.objToJson(entry.getValue()));
		}
		redisDao.putMulti(data);
	}

	@Override
	public Boolean putIfAbsent(String key, Object value) {
		return redisDao.putIfAbsent(key, JSONUtils.objToJson(value));
	}

	@Override
	public Boolean putMultiIfAbsent(Map<String, Object> keyValue) {
		if (keyValue.isEmpty()) {
			return true;
		}
		final Map<String, String> data = new LinkedHashMap<String, String>(keyValue.size());
		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			data.put(entry.getKey(), JSONUtils.objToJson(entry.getValue()));
		}

		return redisDao.putMultiIfAbsent(data);
	}

	/*-------------valueOps get ------------*/
	@Override
	public String get(String key) {
		return redisDao.get(key);
	}

	@Override
	public List<String> getMulti(Collection<String> keys) {

		return redisDao.getMulti(keys);
	}

	/********* (3)、Hash（哈希表 ） ****************************************/

	@Override
	public Long hashSize(String key) {
		return redisDao.hashSize(key);
	}

	@Override
	public Map<String, String> hashEntries(String key) {
		return redisDao.hashEntries(key);
	}

	@Override
	public Set<String> hashKeys(String key) {
		return redisDao.hashKeys(key);
	}

	@Override
	public List<String> hashValues(String key) {
		return redisDao.hashValues(key);
	}

	@Override
	public Boolean hashHasKey(String key, String hashKey) {
		return redisDao.hashHasKey(key, hashKey);
	}

	@Override
	public Long hashDelete(String key, String hashKey) {
		return redisDao.hashDelete(key, hashKey);
	}

	@Override
	public String hashGet(String key, String hashKey) {
		return redisDao.hashGet(key, hashKey);
	}

	@Override
	public List<String> hashGetMulti(String key, Collection<String> hashKeys) {
		return redisDao.hashGetMulti(key, hashKeys);
	}

	@Override
	public void hashPut(String key, String hashKey, Object value) {
		redisDao.hashPut(key, hashKey, JSONUtils.objToJson(value));
	}

	@Override
	public Boolean hashPutIfAbsent(String key, String hashKey, Object value) {
		return redisDao.hashPutIfAbsent(key, hashKey, JSONUtils.objToJson(value));
	}

	@Override
	public void hashPutAllOfString(String key, Map<String, String> valueMap) {
		redisDao.hashPutAllOfString(key, valueMap);
	}

	@Override
	public void hashPutAllOfVo(String key, Map<String, Object> valueMap) {
		Map<String, String> dataMap = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
			dataMap.put(entry.getKey(), JSONUtils.objToJson(entry.getValue()));
		}
		redisDao.hashPutAllOfString(key, dataMap);
	}

	/*
	 * @Override public void hashScan(String key, long count, IHashScanExecutor
	 * hashScanExecutor){ redisDao.hashScan(key, count, hashScanExecutor); }
	 * 
	 * @Override public void hashScan(String key, IHashScanExecutor
	 * hashScanExecutor){ long count = redisDao.hashSize(key);
	 * redisDao.hashScan(key, count ,hashScanExecutor); }
	 */

	/********* (4)、List（列表） *****************************************/
	/**
	 * 同方向进出就是----堆栈操作 不同方向进出就是----队列操作 这里只提供：左进、左出、右出
	 */

	@Override
	public Long listSize(String key) {
		return redisDao.listSize(key);
	}

	@Override
	public void listSetIndex(String key, long index, Object value) {
		redisDao.listSetIndex(key, index, JSONUtils.objToJson(value));
	}

	@Override
	public Long listRemove(String key, long i, Object value) {
		return redisDao.listRemove(key, i, JSONUtils.objToJson(value));
	}

	@Override
	public void listTrim(String key, long start, long end) {
		redisDao.listTrim(key, start, end);
	}

	@Override
	public List<String> listRange(String key, long start, long end) {
		return redisDao.listRange(key, start, end);
	}

	@Override
	public Long listLeftPush(String key, Object value) {
		return redisDao.listLeftPush(key, JSONUtils.objToJson(value));
	}

	@Override
	public Long listLeftPushIfPresent(String key, Object value) {
		return redisDao.listLeftPushIfPresent(key, JSONUtils.objToJson(value));
	}

	@Override
	public String listLeftPop(String key) {
		return redisDao.listLeftPop(key);
	}

	@Override
	public String listRightPop(String key) {
		return redisDao.listRightPop(key);
	}

	@Override
	public String listRightPopAndLeftPush(String key, String newKey) {
		return redisDao.listRightPopAndLeftPush(key, newKey);
	}

	@Override
	public String listRightPopAndLeftPush(String key, String newKey, long timeout, TimeUnit unit) {
		return redisDao.listRightPopAndLeftPush(key, newKey, timeout, unit);
	}

	/********* (5)、Set（集合） ****************************************/

	@Override
	public Long setSize(String key) {
		return redisDao.setSize(key);
	}

	@Override
	public void setAdd(String key, Object obj) {
		redisDao.setAdd(key, JSONUtils.objToJson(obj));
	}

	@Override
	public void setAdd(String key, List<Object> valueList) {
		for (Object value : valueList) {
			redisDao.setAdd(key, JSONUtils.objToJson(value));
		}
	}

	@Override
	public List<String> setMembers(String key) {
		List<String> valueList = new ArrayList<String>();
		Set<String> valueSet = redisDao.setMembers(key);
		valueList.addAll(valueSet);
		return valueList;
	}

	@Override
	public Boolean setIsMember(String key, Object obj) {
		return redisDao.setIsMember(key, JSONUtils.objToJson(obj));
	}

	@Override
	public Long setRemove(String key, Object obj) {
		return redisDao.setRemove(key, JSONUtils.objToJson(obj));
	}

	/********* (6)、ZSet（有序集合） ****************************************/

	@Override
	public Long zsetSize(String key) {
		return redisDao.zsetSize(key);
	}

	@Override
	public void zsetClear(String key) {
		redisDao.zsetClear(key);
	}

	@Override
	public void zsetAdd(String key, String obj, double score) {
		redisDao.zsetAdd(key, obj, score);
	}

	@Override
	public void zsetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
		redisDao.zsetAdd(key, tuples);
	}

	@Override
	public Set<String> zsetGetRange(String key, long start, long end) {
		return redisDao.zsetGetRange(key, start, end);
	}

	@Override
	public Set<String> zsetGetRevRange(String key, long start, long end) {
		return redisDao.zsetGetRevRange(key, start, end);
	}

	@Override
	public Long zsetRemove(String key, Object obj) {
		return redisDao.zsetRemove(key, JSONUtils.objToJson(obj));
	}

	@Override
	public Long zsetRank(String key, Object obj) {
		return redisDao.zsetRank(key, JSONUtils.objToJson(obj));
	}

	@Override
	public Double zsetScore(String key, Object obj) {
		return redisDao.zsetScore(key, JSONUtils.objToJson(obj));
	}

}
