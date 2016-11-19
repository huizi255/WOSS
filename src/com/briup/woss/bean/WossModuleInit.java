package com.briup.woss.bean;

import java.util.Date;
import java.util.Properties;
/**
 * 该接口是除配置模块以外的所有模块的父接口。
 * 用于模块接收初始化配置信息和注入配置模块。 
 * 
 * @author wang
 * @version new {@link Date}
 *  
 */
public interface WossModuleInit {
	/**
	 * 将所需要的配置信息传递进该类，该类得到配置信息后进行初始化。
	 * @param properties 
	 * 一个Properties实例封装了初始化所需的各种配置信息
	 */
	void init(Properties properties);
}
