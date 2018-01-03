package com.tcl.xpay.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * <br>
 * <b>功能：</b>详细的功能描述 <br>
 * 公用缓存接口常用接口封装，系统所有平台模块统一通此接口访问缓存， <br>
 * 统一使用字符串的key方便管理 <br>
 * 分为以下项小项 <br>
 * (1)、Key（键） <br>
 * (2)、String（字符串） <br>
 * (3)、Hash（哈希表） <br>
 * (4)、List（列表） <br>
 * (5)、Set（集合） <br>
 * (6)、ZSet（有序集合）
 */
public interface ICacheService {

	@Value("${CacheService}")
	public static String CacheService = "";// 根据配置文件指定spring注入特定bean

	/********* (1)、Key（键） **********/
	/**
	 * 根据key检查对象是否存在
	 * 
	 * @param key
	 * @return
	 */
	Boolean exists(String key);

	/**
	 * 根据key删除缓存对象
	 * 
	 * @param key
	 */
	void delete(String key);

	/**
	 * 设置key的过期时长，存活 多少秒
	 * 
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	Boolean expire(String key, long timeout, TimeUnit unit);

	/**
	 * 取得key的过期时长（单位为：秒），余下多少秒
	 * 
	 * @param key
	 * @return
	 */
	Long getExpire(String key);

	/**
	 * 取得key的过期时长（单位为：TimeUnit传入的值），余下多少秒
	 * 
	 * @param key
	 * @return
	 */
	Long getExpire(String key, TimeUnit unit);

	/**
	 * key的重命名，将 key改名为 newKey
	 * 
	 * @param key
	 * @param newKey
	 */
	void rename(String key, String newKey);

	/**
	 * 根据key检查缓中数据类型
	 * 
	 * @param key
	 * @return
	 */
	String type(String key);

	/**
	 * 模糊查询key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> findKeys(String pattern);

	// sort功能后期实现，或是没必要实现

	/********* (2)、String（字符串） **********/
	/**
	 * 点击量递增（不确定是否线程安全）
	 * 
	 * @param key
	 * @param offset
	 *            递增值
	 * @return 递增后的值
	 */
	long increment(String key, long offset);

	/**
	 * 存入对象，对象会转为json对象再put进入去 也可以先把list和map转为json字符串存入
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);

	/**
	 * 存入对象,并设置有效期。对象会转为json对象再put进入去
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	void put(String key, Object value, long timeout, TimeUnit unit);

	/**
	 * 保持过期时间的更新
	 * 
	 * @param key
	 * @param value
	 */
	void putKeepTTL(String key, Object value);

	/**
	 * 同时存入多个对象，对象会转为json对象再put进入去 Map<String, Object>
	 * 
	 * @param keyValue
	 */
	void putMulti(Map<String, Object> keyValue);

	/**
	 * 原来没有这个key，才put入对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean putIfAbsent(String key, Object value);

	/**
	 * 同时存入多个对象，对象会转为json对象再put进入去 原来没有这个key，才put入对象 Map<String, Object>
	 * 
	 * @param keyValue
	 */
	Boolean putMultiIfAbsent(Map<String, Object> keyValue);

	/**
	 * 返回对象的json字符串
	 * 
	 * @param key
	 * @return
	 */
	String get(String key);

	/**
	 * 根据传入的集合，返回对应的多个对象的字符串
	 */
	List<String> getMulti(Collection<String> keys);

	/********* (3)、Hash（哈希表 **********/

	/**
	 * 返回key对应的map包含的对象size。返回0表示没有这个key
	 * 
	 * @param key
	 * @return
	 */
	Long hashSize(String key);

	/**
	 * 根据key返回Map，注意map里面都是json——String 一般不建议使用
	 * 
	 * @param key
	 * @return
	 */
	Map<String, String> hashEntries(String key);

	/**
	 * 根据key返回对应的map对象的keyset
	 * 
	 * @param key
	 * @return
	 */
	Set<String> hashKeys(String key);

	/**
	 * 根据key返回对应的map对象的valueList
	 * 
	 * @param key
	 * @return
	 */
	List<String> hashValues(String key);

	/**
	 * 判断hash表里面是否有这个对象
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	Boolean hashHasKey(String key, String hashKey);

	/**
	 * 删除hash表中对应的对象
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	Long hashDelete(String key, String hashKey);

	/**
	 * 获取map的value字符串
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	String hashGet(String key, String hashKey);

	/**
	 * 返回hashmap中的多个对象字符串
	 * 
	 * @param key
	 * @param hashKeys
	 * @return
	 */
	List<String> hashGetMulti(String key, Collection<String> hashKeys);

	/**
	 * 向hash表插入一个对象
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	void hashPut(String key, String hashKey, Object value);

	/**
	 * 如果原来没有，向hash表插入一个对象
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	Boolean hashPutIfAbsent(String key, String hashKey, Object value);

	/**
	 * 向hash表插入一个map
	 * 
	 * @param key
	 * @param valueMap
	 */
	void hashPutAllOfString(String key, Map<String, String> valueMap);

	/**
	 * 向hash表插入一个map
	 * 
	 * @param key
	 * @param valueMap
	 */
	void hashPutAllOfVo(String key, Map<String, Object> valueMap);

	/*    *//**
			 * hash scan
			 * 
			 * @param key
			 * @param count
			 *            记录数
			 * @param hashScanExecutor
			 */
	/*
	 * void hashScan(String key,long count , IHashScanExecutor
	 * hashScanExecutor);
	 * 
	 *//**
		 * hash scan
		 * 
		 * @param key
		 * @param hashScanExecutor
		 *//*
		 * void hashScan(String key, IHashScanExecutor hashScanExecutor);
		 */

	/********* (4)、List（列表） **********/
	/**
	 * 同方向进出就是----堆栈操作 不同方向进出就是----队列操作 这里只提供：左进、左出、右出
	 */

	/**
	 * 查询list的对象个数
	 * 
	 * @param key
	 * @return
	 */
	Long listSize(String key);

	/**
	 * 向从左边开始的第n个位置设置一个新的对象
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	void listSetIndex(String key, long index, Object value);

	/**
	 * 删除一个对象
	 * 
	 * @param key
	 * @param i
	 * @param value
	 * @return
	 */
	Long listRemove(String key, long i, Object value);

	/**
	 * 裁剪对象
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	void listTrim(String key, long start, long end);

	/**
	 * 检索，返回列表（对象的字符串）
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	List<String> listRange(String key, long start, long end);

	/**
	 * 向list左边压入一个对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Long listLeftPush(String key, Object value);

	/**
	 * 如果key对应的list存在，向list左边压入一个对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Long listLeftPushIfPresent(String key, Object value);

	/**
	 * 返回list左边的字符串
	 * 
	 * @param key
	 * @return
	 */
	String listLeftPop(String key);

	/**
	 * 向list右边拿出一个字符串
	 * 
	 * @param key
	 * @return
	 */
	String listRightPop(String key);

	/**
	 * 向list右边拿出一个对象,并put入新list的左边
	 * 
	 * @param key
	 * @param newKey
	 * @return
	 */
	String listRightPopAndLeftPush(String key, String newKey);

	/**
	 * 向list右边拿出一个对象,并put入新list的左边
	 * 
	 * @param key
	 * @param newKey
	 * @param timeout
	 * @param unit
	 * @return
	 */
	String listRightPopAndLeftPush(String key, String newKey, long timeout, TimeUnit unit);

	/********* (5)、Set（集合） **********/

	/**
	 * 查询set元素的个数
	 * 
	 * @param key
	 * @return
	 */
	Long setSize(String key);

	/**
	 * 向set中加入一个元素
	 * 
	 * @param key
	 * @param obj
	 */
	void setAdd(String key, Object obj);

	/**
	 * 批量向set中加入元素
	 * 
	 * @param key
	 * @param valueList
	 */
	void setAdd(String key, List<Object> valueList);

	/**
	 * 返回key对应的set集合
	 * 
	 * @param key
	 * @return
	 */
	List<String> setMembers(String key);

	/**
	 * 判断某元素是否在set集合中
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	Boolean setIsMember(String key, Object obj);

	/**
	 * 向set中删除某个对象
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	Long setRemove(String key, Object obj);

	/********* (6)、ZSet（有序集合） **********/

	/**
	 * 有序集合的大小
	 * 
	 * @param key
	 * @return
	 */
	Long zsetSize(String key);

	/**
	 * 清空有序集合
	 * 
	 * @param key
	 */
	void zsetClear(String key);

	/**
	 * 添加元素集合到有序集合
	 * 
	 * @param key
	 * @param obj
	 * @param score
	 */
	void zsetAdd(String key, String obj, double score);

	/**
	 * 添加元素集合到有序集合
	 * 
	 * @param key
	 * @param tuples
	 */
	void zsetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples);

	/**
	 * 正序获取范围集合
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return Set<String>
	 */
	Set<String> zsetGetRange(String key, long start, long end);

	/**
	 * 反序获取范围集合
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return Set<String>
	 */
	Set<String> zsetGetRevRange(String key, long start, long end);

	/**
	 * 删除一个元素
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	Long zsetRemove(String key, Object obj);

	/**
	 * 得到一个元素当前排名
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	Long zsetRank(String key, Object obj);

	/**
	 * 返回某个元素的得分
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	Double zsetScore(String key, Object obj);

}
