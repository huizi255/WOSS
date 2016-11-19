package com.briup.woss.common;

import java.util.Date;

import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;
/**
 * Configuration接口提供了配置模块的规范。
 * 配置模块通过某种配置方式将Logger、
 * BackUP、Gather、Client、Server、
 * DBStore等模块的实现类进行实例化， 
 * 并且将其所需要配置信息予以传递。
 * 通过配置模块可以获得各个模块的实例。 
 * @author wang
 * @version new {@link Date}
 */
public interface Configuration {
	/**
	 * 获取备份模块的实例
	 * @return
	 * @throws Exception
	 */
	BackUP getBackup() throws Exception;
	/**
	 * 获取日志模块的实例 
	 * @return
	 * @throws Exception
	 */
	Logger getLogger() throws Exception;
	/**
	 * 获取服务器端的实例
	 * @return
	 * @throws Exception
	 */
	Server getServer() throws Exception;
	/**
	 * 获取入库模块的实例 
	 * @return
	 * @throws Exception
	 */
	DBStore getDbStore() throws Exception;
	/**
	 * 获取客户端的实例
	 * @return
	 * @throws Exception
	 */
	Client getClient() throws Exception;
	/**
	 * 获取采集模块的实例 
	 * @return
	 * @throws Exception
	 */
	Gather getGather() throws Exception;
}
