package com.briup.woss.common;

import java.util.Date;

import com.briup.woss.bean.WossModuleInit;
/**
 * 该接口提供了日志模块的规范。 
 * 日志模块将日志信息划分为五种级别，
 * 具体不同级别的日志的记录的格式、
 * 记录方式等内容有具体实现类来决定。 
 * @author wang
 * @version new {@link Date}
 * @see com.briup.woss.WossModuleInit
 *
 */
public interface Logger extends WossModuleInit {
	/**
	 * 记录Debug级别的日志
	 * @param msg 需要记录的日志信息
	 */
	void debug(String msg);
	/**
	 * 记录Error级别的日志
	 * @param msg 需要记录的日志信息
	 */
	void error(String msg);
	/**
	 * 记录Fatal级别的日志
	 * @param msg 需要记录的日志信息
	 */
	void fatal(String msg);
	/**
	 * 记录Info级别的日志
	 * @param msg 需要记录的日志信息
	 */
	void info(String msg);
	/**
	 * 记录Warn级别的日志
	 * @param msg 需要记录的日志信息
	 */
	void warn(String msg);
}
