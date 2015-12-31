package com.hqukai.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author hankai
 *
 *         20141205
 *
 */
public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
	private final static Map<String, Properties> cache = new HashMap<String, Properties>();
	private final static Map<String, Properties> cacheAbs = new HashMap<String, Properties>();

	/**
	 * 通过相对路径获取resources目录下properties文件中的属性值
	 *
	 * @param c 调用者的Class,用于获取程序运行所在路径
	 * @param relativePath
	 *            相对路径 为resources目录下的相对路径
	 * @param key
	 * @return
	 * @throws Exception
	 *
	 *             hankai 20141205
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static String getValueFromRelativePath(Class c, String relativePath, String key) throws Exception {
		String path = PathUtils.getResoucesPath(c) + relativePath;
		File pFile = new File(path);
		FileInputStream pInStream = new FileInputStream(pFile);
		Properties pp = new Properties();
		pp.load(pInStream);
		return pp.get(key).toString();
	}

	/**
	 *
	 * 获取绝对路径下properties文件中的属性值， absolutePathName传绝对路径
	 *
	 * @param absolutePathName 绝对路径
	 * @param key
	 * @return
	 * @throws Exception
	 * hankai 20141205
	 *
	 */
	public static String getValueFromAbsolutePath(String absolutePathName, String key) throws Exception {
		File pFile = new File(absolutePathName);
		FileInputStream pInStream = new FileInputStream(pFile);
		Properties pp = new Properties();
		pp.load(pInStream);
		return pp.get(key).toString();
	}

	/**
	 * 根据属性文件和属性名获取配置属性置
	 *  @param c 调用者的Class,用于获取程序运行所在路径
	 * @param relativePath 属性文件相对路径含文件名称
	 * @param propName 属性名
	 * @return
	 */
	public static String getValRelPath(Class<?> c, String relativePath, String propName) {
		Properties pp = cache.get(relativePath);
		if (null == pp) {
			try {
				pp = initPropertiesRelPath(c, relativePath);
				cache.put(relativePath, pp);
			} catch (Exception e) {
				log.error("初始化配置文件" + relativePath + "失败", e);
				throw new RuntimeException("初始化配置文件" + relativePath + "失败", e);
			}
		}
		return pp.getProperty(propName);
	}

	/**
	 * 根据属性文件和属性名获取配置属性置
	 *
	 * @param absolutePath 绝对路径含文件名称
	 * @param propName
	 * @return
	 */
	public static String getValAbsPath(String absolutePath, String propName) {
		Properties pp = cacheAbs.get(absolutePath);
		if (null == pp) {
			try {
				pp = initPropertiesAbsPath(absolutePath);
				cacheAbs.put(absolutePath, pp);
			} catch (Exception e) {
				log.error("初始化配置文件" + absolutePath + "失败", e);
				throw new RuntimeException("初始化配置文件" + absolutePath + "失败", e);
			}
		}
		return pp.getProperty(propName);
	}

	public static Properties initPropertiesRelPath(Class<?> c, String relativePath) throws IOException {
		String path = PathUtils.getResoucesPath(c) + relativePath;
		File pFile = new File(path);
		FileInputStream pInStream = new FileInputStream(pFile);
		Properties pp = new Properties();
		pp.load(pInStream);
		return pp;
	}

	public static Properties initPropertiesAbsPath(String absolutePathName) throws Exception {
		File pFile = new File(absolutePathName);
		FileInputStream pInStream = new FileInputStream(pFile);
		Properties pp = new Properties();
		pp.load(pInStream);
		return pp;
	}
}
